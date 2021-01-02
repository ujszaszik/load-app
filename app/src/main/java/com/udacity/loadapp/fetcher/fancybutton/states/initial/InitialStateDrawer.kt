package com.udacity.loadapp.fetcher.fancybutton.states.initial

import android.graphics.Canvas
import android.graphics.Rect
import com.udacity.loadapp.fetcher.fancybutton.*
import com.udacity.loadapp.fetcher.fancybutton.states.ButtonStateDrawer

object InitialStateDrawer :
    ButtonStateDrawer {

    private lateinit var textToDraw: String

    private lateinit var textBounds: Rect

    private val backgroundPaint = Painters.getBackgroundPaint()
    private val textPaint = Painters.getTextPaint()

    override fun draw(view: FancyButton, canvas: Canvas) {
        initializeTextToDraw(view)
        initializeTextBounds()
        val params = view.params
        canvas.drawRect(params.bgRect, backgroundPaint.apply { color = params.bgRectColor })
        drawText(view, canvas)
    }

    private fun initializeTextToDraw(view: FancyButton) {
        if (!this::textToDraw.isInitialized) textToDraw = view.params.initialText
    }

    private fun initializeTextBounds() {
        if (!this::textBounds.isInitialized) {
            textBounds =
                Rect().also { textPaint.getTextBounds(textToDraw, 0, textToDraw.length, it) }
        }
    }

    private fun drawText(view: FancyButton, canvas: Canvas) {
        canvas.drawText(
            textToDraw,
            view.horizontalCenter(),
            view.verticalCenter() + textBounds.verticalCenter(),
            textPaint
        )
    }

}