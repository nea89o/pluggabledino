package de.romjaki.pluggabledino.api

import com.beust.klaxon.Klaxon
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.*


object PluginLoader {
    private val _plugins: MutableMap<String, IPlugin> = mutableMapOf()
    private val klaxon = Klaxon()
    private const val pluginFolder = "plugins"

    private var devPlugin: String? = null

    val plugins
        get() = _plugins.toMap()


    fun loadPlugin(url: URL): IPlugin? {
        val cl = URLClassLoader(arrayOf(url))
        val pluginInfo = klaxon.parse<PluginMeta>(
                Scanner(
                        cl.getResourceAsStream("plugin.json"))
                        .useDelimiter("\\A")
                        .next()) ?: throw PluginMetaMissingException(url.toString())
        return loadPlugin(pluginInfo, cl.loadClass(pluginInfo.clazz))
    }

    private fun loadPlugin(pluginInfo: PluginMeta, pluginClass: Class<*>): IPlugin? {
        if (!IPlugin::class.java.isAssignableFrom(pluginClass)) {
            return null
        }
        val plugin = pluginClass.newInstance() as IPlugin
        plugin.meta = pluginInfo
        _plugins[pluginInfo.name] = plugin

        return plugin

    }

    private fun checkDependencies() {
        for ((name, instance) in _plugins) {
            for (dep in instance.meta!!.dependencies) {
                if (!_plugins.containsKey(dep.dependencyName)) {
                    throw DependencyMissing(name, dep.dependencyName, dep.dependencyVersion)
                }
                val dependency = _plugins[dep.dependencyName]!!
                if (dep.dependencyVersion.matches(dependency.meta!!.name.toRegex())) {
                    throw DependencyMissing(name, dep.dependencyName, dep.dependencyVersion)
                }
            }
        }
    }

    fun loadPlugins() = loadPlugins(File(pluginFolder))
    fun loadPlugins(folder: File) {
        if (!folder.isDirectory) {
            throw PluginFolderNotFound(folder.absolutePath)
        }
        folder.listFiles { _, filename -> filename.endsWith(".jar") }
                .map {
                    it.toURI().toURL()
                }.forEach { loadPlugin(it) }
        installDevPlugin()
        checkDependencies()
        injectDependencies()
    }

    private fun installDevPlugin() {
        if (devPlugin == null) {
            return
        }
        val clazz = Class.forName(devPlugin)
        val meta = klaxon.parse<PluginMeta>(
                Scanner(
                        clazz
                                .classLoader
                                .getResourceAsStream("plugin.json"))
                        .useDelimiter("\\A")
                        .next()) ?: throw PluginMetaMissingException("<dev>")
        loadPlugin(meta, clazz)
    }


    private fun injectDependencies() {
        for ((name, instance) in _plugins) {
            for (field in instance.javaClass.fields) {
                val inject = field.getAnnotation(Inject::class.java) as Inject
                if (_plugins.containsKey(inject.what)) {
                    field.set(instance, _plugins[inject.what])
                } else {
                    throw InjectException(name, inject.what)
                }
            }
        }
    }

    fun loadDevPlugin(className: String) {
        devPlugin = className
    }
}