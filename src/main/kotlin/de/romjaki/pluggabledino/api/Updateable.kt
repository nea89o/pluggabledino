package de.romjaki.pluggabledino.api

import org.newdawn.slick.Input

interface Updateable {
    fun update(input: Input)
    fun enter()
}
