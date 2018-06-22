package de.romjaki.pluggabledino.api

class PluginMetaMissingException(s: String) : Throwable("$s plugin is missing a plugin.json")
