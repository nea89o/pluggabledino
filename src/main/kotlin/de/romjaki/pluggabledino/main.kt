package de.romjaki.pluggabledino

import de.romjaki.pluggabledino.api.Events
import de.romjaki.pluggabledino.api.PluginLoader
import de.romjaki.pluggabledino.events.InitEvent
import de.romjaki.pluggabledino.events.PostInitEvent
import de.romjaki.pluggabledino.events.PreInitEvent
import de.romjaki.pluggabledino.states.GameState
import de.romjaki.pluggabledino.states.MainMenu
import de.romjaki.pluggabledino.states.SettingsState
import de.romjaki.pluggabledino.states.SplashScreen
import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Input
import org.newdawn.slick.state.StateBasedGame


const val WIDTH = 640
const val HEIGHT = 480
const val FPS = 60
const val VERSION = 1.0

const val SPLASHSCREEN = 0
const val MAINMENU = 1
const val SETTINGS = 3
const val GAME = 2

fun main(args: Array<String>) {
    PluginLoader.loadPlugins()
    Input.disableControllers()
    val app = AppGameContainer(Application())
    app.setDisplayMode(WIDTH, HEIGHT, false)
    app.setTargetFrameRate(FPS)
    app.setShowFPS(true)
    Events.broadcastEvent(PreInitEvent())
    Events.broadcastEvent(InitEvent())
    Events.broadcastEvent(PostInitEvent())
    app.start()

}

class Application : StateBasedGame("Dino Game v$VERSION") {
    override fun initStatesList(container: GameContainer?) {
        addState(SplashScreen())
        addState(MainMenu())
        addState(SettingsState())
        addState(GameState())
    }
}