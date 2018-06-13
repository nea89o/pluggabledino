package de.romjaki.pluggabledino

import org.newdawn.slick.Animation
import org.newdawn.slick.Image
import org.newdawn.slick.SpriteSheet
import org.newdawn.slick.UnicodeFont
import org.newdawn.slick.opengl.TextureLoader
import java.awt.Font
import java.io.FileInputStream

val splash = Image(TextureLoader.getTexture("PNG", FileInputStream("assets/images/splash.png")))
val dinoSprites = SpriteSheet("assets/images/characters.png", 32, 32)
val dinoAnimated = Animation(dinoSprites, intArrayOf(
        0, 0,
        1, 0,
        2, 0,
        3, 0,
        4, 0,
        5, 0,
        6, 0,
        7, 0
), IntArray(8) { 100 })
val font = UnicodeFont(Font("Arial", Font.PLAIN, 14))
val buttonImage = Image(TextureLoader.getTexture("PNG", FileInputStream("assets/images/button.png")))

val groundline = Image(TextureLoader.getTexture("PNG", FileInputStream("assets/images/groundline.png")))

val cactusImg = Image(TextureLoader.getTexture("PNG", FileInputStream("assets/images/cactus.png")))

val BirdImg= Image(TextureLoader.getTexture("PNG", FileInputStream("assets/images/bird1.png")))