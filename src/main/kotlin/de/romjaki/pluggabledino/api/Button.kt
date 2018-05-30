package de.romjaki.pluggabledino.api

import de.romjaki.pluggabledino.buttonImage
import de.romjaki.pluggabledino.drawImageCentered
import de.romjaki.pluggabledino.drawStringCentered
import de.romjaki.pluggabledino.font
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import java.awt.Rectangle
import kotlin.math.min

class Button(private val text: String, val x: Float, val y: Float) {

    val width = min(buttonImage.width, font.getWidth(text) + 10)
    val image = buttonImage.getScaledCopy(width, buttonImage.height)

    private var lastClicked = false

    val leftX
        get() = x - width / 2
    val rightX
        get() = x + width / 2
    val topY
        get() = y - image.height / 2
    val bottomY
        get() = y + image.height / 2

    val rectangle
        get() = Rectangle(leftX.toInt(), topY.toInt(), (rightX - leftX).toInt(), (bottomY - topY).toInt())


    private val clickHandlers = mutableListOf<() -> Unit>()

    fun addClickHandler(handler: () -> Unit) {
        clickHandlers.add(handler)
    }

    fun draw(g: Graphics) {
        g.drawImageCentered(image, x, y)
        g.drawStringCentered(text, x, y)
    }

    override fun toString(): String =
            "X: $leftX - $rightX, $topY - $bottomY, width=$width"

    fun enter() {
        lastClicked = true
    }

    fun isClicked(input: Input): Boolean =
            input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && isMouseOver(input)

    fun update(input: Input) {
        val ret = isClicked(input)
        if (!lastClicked && ret) {
            clickHandlers.forEach({ it() })
        }
        lastClicked = ret
    }

    fun isMouseOver(input: Input): Boolean =
            rectangle.contains(input.mouseX, input.mouseY)

}

