package com.udacity.loadapp.fetcher.fancybutton.states.finished

import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.res.ResourcesCompat
import com.udacity.loadapp.R
import com.udacity.loadapp.fetcher.fancybutton.states.ButtonStateDrawer
import com.udacity.loadapp.fetcher.fancybutton.FancyButton
import com.udacity.loadapp.fetcher.fancybutton.Painters

object DownloadFinishedIconDrawer :
    ButtonStateDrawer {

    private const val CORNER_RADIUS = 100f

    private lateinit var view: FancyButton

    override fun draw(view: FancyButton, canvas: Canvas) {
        this.view = view
        drawBackground(view, canvas)
        val drawable = ResourcesCompat.getDrawable(view.resources, getAppropriateDrawableId(), null)
        drawable?.setBounds(0, 0, view.width, view.height);
        drawable?.draw(canvas)
    }

    private fun drawBackground(view: FancyButton, canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            view.width.toFloat(),
            view.height.toFloat(),
            CORNER_RADIUS,
            CORNER_RADIUS,
            getAppropriatePaint()
        )
    }

    private fun getAppropriatePaint(): Paint {
        return if (view.isDownloadSuccessful) Painters.getBackgroundPaint()
        else Painters.getErrorBackgroundPaint()
    }

    private fun getAppropriateDrawableId(): Int {
        return if (view.isDownloadSuccessful) R.drawable.ic_success_white
        else R.drawable.ic_error_white
    }
}