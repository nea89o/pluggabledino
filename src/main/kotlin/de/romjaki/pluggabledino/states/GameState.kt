package de.romjaki.pluggabledino.states

import de.romjaki.pluggabledino.*
import de.romjaki.pluggabledino.game.GameWorld
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

class GameState : BasicGameState() {
    override fun init(container: GameContainer?, game: StateBasedGame?) {
    }

    override fun enter(container: GameContainer?, game: StateBasedGame?) {
        world = GameWorld()
    }

    override fun update(container: GameContainer?, game: StateBasedGame?, delta: Int) {
        if (container!!.input.isKeyDown(Input.KEY_R)) {
            world = GameWorld()
        }
        if (world.hurt) {
            game!!.enterState(LOST)
        }
        world.update(delta / 1000f, container.input)
        dinoAnimated.update(delta.toLong())
    }

    lateinit var world: GameWorld

    override fun getID(): Int =
            GAME


    override fun render(container: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        g!!
        g.scale(WIDTH_RATIO, HEIGHT_RATIO)
        g.background = Color.white
        if (world.hurt) {
            g.background = Color.red
        }
        g.color = Color.green
        g.drawImage(dinoAnimated.currentFrame, world.dino.position.x * WIDTH / 50, world.dino.position.y * HEIGHT / 50 - dinoAnimated.height)
        g.drawImage(groundline, 0f, HEIGHT * 39 / 50f)
        for (cactus in world.cacti) {
            g.drawImage(cactusImg, cactus.position.x * WIDTH / 50, cactus.position.y * HEIGHT / 50 - cactusImg.height)
        }
    }

}
