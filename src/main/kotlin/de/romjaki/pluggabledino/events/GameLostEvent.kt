package de.romjaki.pluggabledino.events

import de.romjaki.pluggabledino.api.Event

data class GameLostEvent(val score: Int) : Event