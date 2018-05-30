package de.romjaki.pluggabledino.api

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Inject(val what: String)
