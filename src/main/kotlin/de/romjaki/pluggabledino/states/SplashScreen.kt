package de.romjaki.pluggabledino.states

import de.romjaki.pluggabledino.*
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.Input
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame


class SplashScreen : BasicGameState() {
    override fun init(container: GameContainer?, game: StateBasedGame?) {
    }

    override fun update(container: GameContainer?, game: StateBasedGame?, delta: Int) {
        container!!
        game!!
        val input = container.input
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            input.clearMousePressedRecord()
            game.enterState(MAINMENU)
        }
        if (input.isKeyDown(Input.KEY_Q)) {
            System.exit(0)
        }
    }

    override fun getID(): Int =
            SPLASHSCREEN


    override fun render(container: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        g!!
        g.scale(WIDTH_RATIO, HEIGHT_RATIO)
        g.drawImage(splash, 0f, 0f)
        g.drawStringCentered("CLICK ANYWHERE TO CONTINUE", WIDTH / 2f, HEIGHT / 2f)
    }

}


