package de.romjaki.pluggabledino.api

class InjectException(plugin: String, dependency: String) : Exception("$plugin failed to load $dependency")