package de.romjaki.pluggabledino.states

import de.romjaki.pluggabledino.*
import de.romjaki.pluggabledino.api.Events
import de.romjaki.pluggabledino.events.GameLostEvent
import de.romjaki.pluggabledino.events.GameRenderEvent
import de.romjaki.pluggabledino.events.GameUpdateEvent
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
        anthemSound.loop()
        count = 0
    }

    override fun leave(container: GameContainer?, game: StateBasedGame?) {
        anthemSound.pause()
    }

    override fun update(container: GameContainer?, game: StateBasedGame?, delta: Int) {
        game!!
        count += delta

        if (container!!.input.isKeyDown(Input.KEY_R)) {
            world = GameWorld()
        }
        if (world.hurt) {
            lastscore = count / 100
            Events.broadcastEvent(GameLostEvent(lastscore))
            return game.enterState(LOST)
        }
        world.update(delta / 1000f, container.input)
        dinoAnimated.update(delta.toLong())
        Events.broadcastEvent(GameUpdateEvent(game, delta, container, world))
    }

    lateinit var world: GameWorld

    var count: Int = 0

    override fun getID(): Int =
            GAME


    override fun render(container: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        g!!
        container!!
        game!!

        g.scale(WIDTH_RATIO, HEIGHT_RATIO)
        g.drawStringCentered((count / 100).toString(), WIDTH / 2f, HEIGHT / 2f)
        g.background = Color.white
        if (world.hurt) {
            g.background = Color.red
        }
        g.color = Color.green
        g.drawImage(background, 0f, 0f)
        g.drawImage(dinoAnimated.currentFrame, world.dino.position.x * WIDTH / 50, world.dino.position.y * HEIGHT / 50 - dinoAnimated.height)
        g.drawImage(groundline, 0f, HEIGHT * 39 / 50f)
        for (cactus in world.cacti) {
            g.drawImage(cactusImg, cactus.position.x * WIDTH / 50, cactus.position.y * HEIGHT / 50 - cactusImg.height)
        }
        for (bird in world.birdd) {
            g.drawImage(BirdImg, bird.position.x * WIDTH / 50, bird.position.y * HEIGHT / 50 - BirdImg.height)
        }
        Events.broadcastEvent(GameRenderEvent(g, game, container))

    }

}
