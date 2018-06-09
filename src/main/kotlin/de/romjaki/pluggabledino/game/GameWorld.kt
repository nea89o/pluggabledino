package de.romjaki.pluggabledino.game

import org.jbox2d.callbacks.ContactImpulse
import org.jbox2d.callbacks.ContactListener
import org.jbox2d.collision.Manifold
import org.jbox2d.collision.shapes.PolygonShape
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.*
import org.jbox2d.dynamics.contacts.Contact
import org.newdawn.slick.Input

class GameWorld : ContactListener {
    override fun endContact(contact: Contact?) {

    }

    override fun beginContact(contact: Contact?) {
        contact!!
        val bodies = listOf(contact.fixtureA.body, contact.fixtureB.body)
        if (bodies.contains(dino) && cacti.any { bodies.contains(it) }) {
            hurt = true
        }
    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
    }

    var hurt = false

    val world: World

    val cacti = mutableListOf<Body>()

    val cactiBodyDef: BodyDef

    val groundBody: Body

    val dino: Body

    val groundBodyDef: BodyDef

    val groundBox: PolygonShape

    val dinoDef: BodyDef

    val dinoBox: PolygonShape

    val dinoFixtureDef: FixtureDef

    init {
        val gravity = Vec2(0f, 10f)
        world = World(gravity)

        //#region GROUND
        groundBodyDef = BodyDef()
        groundBodyDef.position.set(0f, 50f)
        groundBody = world.createBody(groundBodyDef)
        groundBox = PolygonShape()
        groundBox.setAsBox(50f, 10f)
        groundBody.createFixture(groundBox, 0f)
        //#endregion

        //#region DINO
        dinoDef = BodyDef()
        dinoDef.type = BodyType.DYNAMIC
        dinoDef.position.set(4f, 39f)
        dino = world.createBody(dinoDef)
        dinoBox = PolygonShape()
        dinoBox.setAsBox(1f, 1f)
        dinoFixtureDef = FixtureDef()
        dinoFixtureDef.shape = dinoBox
        dinoFixtureDef.density = 0.9f
        dinoFixtureDef.friction = 0.3f
        dino.createFixture(dinoFixtureDef)
        //#endregion

        cactiBodyDef = BodyDef()
        cactiBodyDef.type = BodyType.KINEMATIC

        createCactus()
    }

    fun createCactus() {
        val body = world.createBody(cactiBodyDef)
        body.position.set(20f, 39f)
        val shape = PolygonShape()
        shape.setAsBox(1f, 1f)
        val cactiFixtureDef = FixtureDef()
        cactiFixtureDef.shape = dinoBox
        cactiFixtureDef.isSensor = true
        cactiFixtureDef.density = 0.1f
        cactiFixtureDef.friction = 0f
        body.createFixture(cactiFixtureDef)
        cacti.add(body)
    }


    fun update(delta: Float, input: Input) {
        if (input.isKeyDown(Input.KEY_UP)) {
            if (isDinoOnGround()) {
                print("Jump")
                dino.applyForceToCenter(Vec2(0f, -2000f))
            }
        }
        cacti.forEach {
            it.linearVelocity.set(-delta * 1000, 0f)
        }
        world.step(delta, 4, 3)
        world.setContactListener(this)
    }

    private fun isDinoOnGround(): Boolean =
            dino.linearVelocity.y == 0.0f

}

