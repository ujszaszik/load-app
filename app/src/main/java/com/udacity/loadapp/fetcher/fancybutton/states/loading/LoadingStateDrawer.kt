package com.udacity.loadapp.fetcher.fancybutton.states.loading

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import com.udacity.loadapp.fetcher.fancybutton.*
import com.udacity.loadapp.fetcher.fancybutton.params.ButtonParams
import com.udacity.loadapp.fetcher.fancybutton.states.ButtonStateDrawer

object LoadingStateDrawer :
    ButtonStateDrawer {

    private const val CIRCLE_DIAMETER = 60f
    private const val CIRCLE_PADDING = 40f
    private const val START_ANGLE = 0f

    private lateinit var textBounds: Rect
    private lateinit var textToDraw: String

    private val backgroundPaint = Painters.getBackgroundPaint()
    private val progressPaint = Painters.getProgressPaint()
    private val textPaint = Painters.getTextPaint()
    private val circlePaint = Painters.getCirclePaint()

    private lateinit var params: ButtonParams

    override fun draw(view: FancyButton, canvas: Canvas) {
        initializeTextToDraw(view)
        initializeTextBounds()
        params = view.params
        canvas.drawRect(params.bgRect, backgroundPaint.apply { color = params.bgRectColor })
        canvas.drawRect(params.progressRect, progressPaint.apply { color = params.loadingColor })
        drawDownloadText(view, canvas)
        drawProgressCircle(view, canvas)
    }

    private fun initializeTextToDraw(view: FancyButton) {
        if (!this::textToDraw.isInitialized) {
            textToDraw = view.params.loadingText
        }
    }

    private fun initializeTextBounds() {
        if (!this::textBounds.isInitialized) {
            textBounds =
                Rect().also { textPaint.getTextBounds(textToDraw, 0, textToDraw.length, it) }
        }
    }

    private fun drawDownloadText(view: FancyButton, canvas: Canvas) {
        canvas.drawText(
            textToDraw,
            view.horizontalCenter() - textPaint.measureText(textToDraw).half()
                    + CIRCLE_DIAMETER.half(),
            view.verticalCenter() + textBounds.verticalCenter(),
            textPaint
        )
    }

    private fun drawProgressCircle(view: FancyButton, canvas: Canvas) {
        canvas.drawArc(
            RectF(
                view.horizontalCenter() + textBounds.horizontalCenter() - CIRCLE_DIAMETER.half(),
                CIRCLE_PADDING,
                view.horizontalCenter() + textBounds.horizontalCenter() + CIRCLE_DIAMETER.half(),
                view.height - CIRCLE_PADDING
            ),
            START_ANGLE, params.sweepAngle, true,
            circlePaint
        )
    }

}