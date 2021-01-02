package com.udacity.loadapp.fetcher.fancybutton.states.finished

import android.animation.ValueAnimator
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.doOnEnd
import com.udacity.loadapp.fetcher.fancybutton.FancyButton
import com.udacity.loadapp.fetcher.fancybutton.rectByLayoutParamsOf

class DownloadFinishedTransitionAnimator(private val view: FancyButton) {

    private val params = view.params

    companion object {
        private const val MAX_PERCENTAGE = 100
        private const val MIN_PERCENTAGE = 20
        private const val DURATION = 300L
    }

    fun start() {
        val startingWidth = view.width
        ValueAnimator.ofInt(MAX_PERCENTAGE, MIN_PERCENTAGE).apply {
            duration = DURATION
            interpolator = AccelerateInterpolator()
            addUpdateListener { animateTransitionToCircle(startingWidth, animatedValue as Int) }
            doOnEnd { view.onAnimationFinished() }
        }.start()
    }

    private fun animateTransitionToCircle(startingWidth: Int, animatedValue: Int) {
        setLayoutWidth(startingWidth, animatedValue)
        setCornerRadius(animatedValue)
        params.bgRect = rectByLayoutParamsOf(view)
        view.invalidate()
    }

    private fun setLayoutWidth(startingWidth: Int, animatedValue: Int) {
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.width = startingWidth.times(animatedValue) / MAX_PERCENTAGE
        view.layoutParams = layoutParams
    }

    private fun setCornerRadius(animatedValue: Int) {
        params.roundRectRadius = MAX_PERCENTAGE - animatedValue.toFloat()
    }

}