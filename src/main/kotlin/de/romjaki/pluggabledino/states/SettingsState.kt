package de.romjaki.pluggabledino.states

import de.romjaki.pluggabledino.*
import de.romjaki.pluggabledino.api.Button
import de.romjaki.pluggabledino.api.ToggleButton
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

class SettingsState : BasicGameState() {
    override fun init(container: GameContainer?, game: StateBasedGame?) {
        game!!
        backButton.addClickHandler {
            game.enterState(MAINMENU)
        }
    }

    override fun enter(container: GameContainer?, game: StateBasedGame?) {
        backButton.enter()
        scoreButton.enter()
    }

    override fun update(container: GameContainer?, game: StateBasedGame?, delta: Int) {
        container!!
        scoreButton.update(container.input)
        backButton.update(container.input)
    }

    override fun getID(): Int =
            SETTINGS

    val scoreButton = ToggleButton(listOf("ON", "OFF"), WIDTH * 3 / 4f, HEIGHT / 8f)

    val backButton = Button("BACK", WIDTH / 2f, HEIGHT / 8 * 7f)

    override fun render(container: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        g!!
        g.scale(WIDTH_RATIO, HEIGHT_RATIO)
        g.background = Color.lightGray
        backButton.draw(g)
        scoreButton.draw(g)
    }

}
