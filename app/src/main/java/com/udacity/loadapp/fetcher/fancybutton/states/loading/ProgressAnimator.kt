package com.udacity.loadapp.fetcher.fancybutton.states.loading

import android.animation.ValueAnimator
import android.graphics.Rect
import androidx.core.animation.doOnEnd
import com.udacity.loadapp.fetcher.fancybutton.FancyButton

class ProgressAnimator(private val view: FancyButton) {

    companion object {
        private const val PROGRESS_MIN = 0
        private const val PROGRESS_MAX = 100
        private const val DURATION = 3000L
    }

    fun start() {
        ValueAnimator.ofInt(PROGRESS_MIN, PROGRESS_MAX).apply {
            duration = DURATION
            addUpdateListener { animateProgress(animatedValue as Int) }
            doOnEnd { this@ProgressAnimator.start() }
        }.start()
    }

    private fun animateProgress(animatedValue: Int) {
        view.params.progressRect =
            Rect(0, 0, view.width.times(animatedValue) / PROGRESS_MAX, view.height)
        view.invalidate()
    }
}