package com.udacity.loadapp.fetcher.fancybutton.states.finished

import android.animation.ValueAnimator
import com.udacity.loadapp.fetcher.fancybutton.FancyButton

class DownloadFinishedColorAnimator(private val view: FancyButton) {

    private val params = view.params

    companion object {
        private const val DURATION = 300L
    }

    fun start() {
        ValueAnimator.ofArgb(params.loadingColor, getColorId())
            .apply {
                duration = DURATION
                addUpdateListener { animateColor(animatedValue as Int) }
            }.start()
    }

    private fun animateColor(animatedValue: Int) {
        params.bgRectColor = animatedValue
        view.invalidate()
    }

    private fun getColorId(): Int {
        return if (view.isDownloadSuccessful) params.successColor
        else params.errorColor
    }
}