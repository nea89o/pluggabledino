package de.romjaki.pluggabledino.events

import de.romjaki.pluggabledino.api.Event
import de.romjaki.pluggabledino.game.GameWorld
import org.newdawn.slick.Game
import org.newdawn.slick.GameContainer

data class GameUpdateEvent(val game: Game, val delta: Int, val container: GameContainer, val world: GameWorld) : Event