package de.romjaki.pluggabledino.api

import de.romjaki.pluggabledino.game.GameWorld

object Emulator {
    fun emulate(delta: Float, shouldJump: (GameWorld) -> Boolean): Float {
        val world = GameWorld()
        var score = 0f
        while (!world.hurt) {
            world.update(delta / 1000f, shouldJump(world))
            score += delta
        }
        return score / 100f
    }
}