package de.romjaki.pluggabledino.events

import de.romjaki.pluggabledino.api.Event
import de.romjaki.pluggabledino.states.SettingsState
import org.newdawn.slick.AppGameContainer

class InitEvent(val app: AppGameContainer, val settings: SettingsState) : Event
