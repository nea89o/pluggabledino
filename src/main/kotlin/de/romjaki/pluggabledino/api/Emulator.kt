package de.romjaki.pluggabledino.api

import de.romjaki.pluggabledino.game.GameWorld

object Emulator {
    fun emulate(delta: Float, shouldJump: (GameWorld) -> Boolean, deleteBirds: Boolean): Float {
        val world = GameWorld()
        var score = 0f
        while (!world.hurt) {
            if (deleteBirds) {
                world.birdd.clear()
            }
            world.update(delta / 1000f, shouldJump(world))
            score += delta
        }
        return score / 100f
    }
}