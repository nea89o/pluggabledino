package de.romjaki.pluggabledino.api

import com.beust.klaxon.Json

data class PluginMeta(var name: String, var version: String, @Json(name = "class") var clazz: String, var dependencies: MutableList<Dependency> = mutableListOf())