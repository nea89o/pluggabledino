package de.romjaki.pluggabledino.states

import de.romjaki.pluggabledino.*
import de.romjaki.pluggabledino.api.Button
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

class MainMenu : BasicGameState() {
    override fun init(container: GameContainer?, game: StateBasedGame?) {
        game!!
        settingsButton.addClickHandler {
            game.enterState(SETTINGS)
        }
        playButton.addClickHandler {
            game.enterState(GAME)
        }
    }

    override fun enter(container: GameContainer?, game: StateBasedGame?) {
        settingsButton.enter()
        playButton.enter()
    }

    override fun update(container: GameContainer?, game: StateBasedGame?, delta: Int) {
        container!!
        game!!
        dinoAnimated.update(delta.toLong())
        if (container.input.isKeyDown(Input.KEY_Q)) {
            System.exit(0)
        }
        settingsButton.update(container.input)
        playButton.update(container.input)
    }

    override fun getID(): Int =
            MAINMENU

    val playButton = Button("PLAY", WIDTH / 2f, HEIGHT / 2f + 50)
    val settingsButton = Button("SETTINGS", WIDTH / 2f, HEIGHT / 2f + 100)
    override fun render(container: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        g!!
        g.background = Color.lightGray
        g.drawImage(dinoAnimated.currentFrame, WIDTH / 2f - 16, HEIGHT / 2f - 16f)
        playButton.draw(g)
        settingsButton.draw(g)
    }

}
