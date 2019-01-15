package de.romjaki.pluggabledino.api

import de.romjaki.pluggabledino.*
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import java.awt.Rectangle
import kotlin.math.max

typealias ChangeListener = (index: Int) -> Unit

class ToggleButton(private val texts: List<String>, val x: Float, val y: Float) : SettingsElement {
    override fun render(g: Graphics) {
        draw(g)
    }

    val width = max(buttonImage.width, texts.map { font.getWidth(it) + 10 }.max()!!)
    val image = buttonImage.getScaledCopy(width, buttonImage.height)

    private var lastClicked = false
    var index = 0


    val leftX
        get() = x - width / 2
    val rightX
        get() = x + width / 2
    val topY
        get() = y - image.height / 2
    val bottomY
        get() = y + image.height / 2

    private val changeListeners = mutableListOf<ChangeListener>()

    val rectangle
        get() = Rectangle(leftX.toInt(), topY.toInt(), (rightX - leftX).toInt(), (bottomY - topY).toInt())

    fun addChangeListener(block: ChangeListener) {
        changeListeners.add(block)
    }

    fun draw(g: Graphics) {
        g.drawImageCentered(image, x, y)
        g.drawStringCentered(texts[index], x, y)
    }

    override fun toString(): String =
            "X: $leftX - $rightX, $topY - $bottomY, width=$width"

    override fun enter() {
        lastClicked = true
    }

    fun isClicked(input: Input): Boolean =
            input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && isMouseOver(input)

    override fun update(input: Input) {
        val ret = isClicked(input)
        if (!lastClicked && ret) {
            index = (index + 1) % texts.size
            changeListeners.forEach { it(index) }
        }
        lastClicked = ret
    }

    fun isMouseOver(input: Input): Boolean =
            rectangle.contains((input.mouseX / WIDTH_RATIO).toInt(), (input.mouseY / HEIGHT_RATIO).toInt())

}

