package de.romjaki.pluggabledino.api

import java.util.*

object Events {
    fun broadcastEvent(event: Event) {
        for (plugin in PluginLoader.plugins.values) {
            Arrays.stream(plugin.javaClass.methods!!)
                    .filter { it.getAnnotation(EventHandler::class.java) != null }
                    .filter { it.parameterCount == 1 }
                    .filter { it.parameterTypes[0].isInstance(event) }
                    .forEach { it.invoke(plugin, event) }
        }
    }
}