package de.romjaki.pluggabledino

import de.romjaki.pluggabledino.api.Events
import de.romjaki.pluggabledino.api.PluginLoader
import de.romjaki.pluggabledino.events.InitEvent
import de.romjaki.pluggabledino.events.PostInitEvent
import de.romjaki.pluggabledino.events.PreInitEvent
import de.romjaki.pluggabledino.states.*
import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Input
import org.newdawn.slick.state.StateBasedGame


const val WIDTH = 640
const val HEIGHT = 480
const val REAL_WIDTH = 800
const val REAL_HEIGHT = 600
const val WIDTH_RATIO = REAL_WIDTH.toFloat() / WIDTH
const val HEIGHT_RATIO = REAL_HEIGHT.toFloat() / HEIGHT
const val FPS = 60
const val VERSION = 1.0

const val SPLASHSCREEN = 0
const val MAINMENU = 1
const val SETTINGS = 3
const val GAME = 2
const val LOST = 4
var lastscore = 0
var highscore = 0
var score = 0
var eventThreads: MutableList<Thread> = mutableListOf()

lateinit var settings: SettingsState
fun main(args: Array<String>) {
    if (args.size > 1 && args[0] == "dev") {
        PluginLoader.loadDevPlugin(args[1])
    }
    PluginLoader.loadPlugins()
    Input.disableControllers()
    val app = AppGameContainer(Application())
    app.setDisplayMode(REAL_WIDTH, REAL_HEIGHT, false)
    app.setTargetFrameRate(FPS)
    app.setShowFPS(true)
    Events.broadcastEvent(PreInitEvent(app))
    eventThreads.add(Thread {
        Events.broadcastEvent(InitEvent(app, settings))
        Events.broadcastEvent(PostInitEvent(app))
    })
    app.start()

}

class Application : StateBasedGame("Dino Game v$VERSION") {
    override fun initStatesList(container: GameContainer?) {
        addState(SplashScreen())
        addState(MainMenu())
        settings = SettingsState()
        addState(settings)
        addState(GameState())
        addState(LostState())
        eventThreads.forEach(Thread::start)
    }
}
