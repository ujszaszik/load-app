package com.udacity.loadapp.fetcher.fancybutton.states.finished

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.udacity.loadapp.fetcher.fancybutton.states.ButtonStateDrawer
import com.udacity.loadapp.fetcher.fancybutton.FancyButton
import com.udacity.loadapp.fetcher.fancybutton.Painters
import com.udacity.loadapp.fetcher.fancybutton.params.ButtonParams

object DownloadFinishedBackgroundDrawer :
    ButtonStateDrawer {

    private const val CORNER_RADIUS = 100f

    private lateinit var canvas: Canvas
    private lateinit var params: ButtonParams

    override fun draw(view: FancyButton, canvas: Canvas) {
        this.canvas = canvas
        params = view.params
        canvas.drawRoundRect(
            getRectangleToDraw(),
            CORNER_RADIUS,
            CORNER_RADIUS,
            getAppropriatePaint(view)
        )
    }

    private fun getRectangleToDraw(): RectF {
        return params.bgRect.run {
            RectF(
                left.toFloat(),
                top.toFloat(),
                right.toFloat(),
                bottom.toFloat()
            )
        }
    }

    private fun getAppropriatePaint(view: FancyButton): Paint {
        return if (view.isDownloadSuccessful) Painters.getBackgroundPaint()
            .apply { color = params.bgRectColor }
        else Painters.getErrorBackgroundPaint().apply { color = params.bgRectColor }
    }

}