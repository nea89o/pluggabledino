package de.romjaki.pluggabledino

import org.jbox2d.common.Vec2
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.Input

fun Graphics.drawStringCentered(s: String, x: Float, y: Float) {
    val h = font.getHeight(s)
    val w = font.getWidth(s)
    drawString(s, x - w / 2f, y - h / 2f)
}

fun Graphics.drawImageCentered(image: Image, x: Float, y: Float) {
    drawImage(image, x - image.width / 2f, y - image.height / 2)
}

operator fun Vec2.plus(vec2: Vec2): Vec2? = add(vec2)

fun Graphics.drawMousePointer(input: Input) {
    fillOval(input.mouseX - 5f, input.mouseY - 5f, 10f, 10f)
}

