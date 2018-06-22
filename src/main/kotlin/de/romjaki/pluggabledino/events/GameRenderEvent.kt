package de.romjaki.pluggabledino.events

import de.romjaki.pluggabledino.api.Event
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.state.StateBasedGame


data class GameRenderEvent(val graphics: Graphics, val game: StateBasedGame, val container: GameContainer) : Event