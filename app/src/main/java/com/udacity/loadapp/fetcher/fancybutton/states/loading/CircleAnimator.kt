package com.udacity.loadapp.fetcher.fancybutton.states.loading

import android.animation.ValueAnimator
import androidx.core.animation.doOnEnd
import com.udacity.loadapp.fetcher.fancybutton.FancyButton

class CircleAnimator(private val view: FancyButton) {

    companion object {
        private const val MIN_ANGLE = 0
        private const val MAX_ANGLE = 360
        private const val DURATION = 3000L
    }

    fun start() {
        ValueAnimator.ofInt(MIN_ANGLE, MAX_ANGLE).apply {
            duration = DURATION
            addUpdateListener { animateSweepAngle(animatedValue as Int) }
            doOnEnd { this@CircleAnimator.start() }
        }.start()
    }

    private fun animateSweepAngle(animatedValue: Int) {
        view.params.sweepAngle = animatedValue.toFloat()
        view.invalidate()
    }
}