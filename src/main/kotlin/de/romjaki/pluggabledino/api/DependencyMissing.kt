package de.romjaki.pluggabledino.api

class DependencyMissing(plugin: String, dependencyName: String, dependencyVersion: String) :
        Exception("$plugin is missing dependency ${dependencyName}with version $dependencyVersion")
