package com.udacity.loadapp.fetcher.fancybutton.states

import android.graphics.Canvas
import com.udacity.loadapp.fetcher.fancybutton.FancyButton

interface ButtonStateDrawer {

    fun draw(view: FancyButton, canvas: Canvas)
}