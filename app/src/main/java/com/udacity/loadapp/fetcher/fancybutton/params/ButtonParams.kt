package com.udacity.loadapp.fetcher.fancybutton.params

import android.graphics.Color
import android.graphics.Rect
import com.udacity.loadapp.R
import com.udacity.loadapp.fetcher.fancybutton.FancyButton
import com.udacity.loadapp.fetcher.fancybutton.rectStub

class ButtonParams(view: FancyButton) {

    lateinit var bgRect: Rect
    var progressRect = rectStub()

    var sweepAngle = 0f
    var roundRectRadius = 0f

    var initialText: String
    var loadingText: String

    var initialTextColor = 0
    var loadingTextColor = 0

    var bgRectColor = 0
    var loadingColor = 0
    var successColor = 0
    var errorColor = 0

    fun isBgRectInitialized(): Boolean = ::bgRect.isInitialized

    init {
        val attrs = view.context.obtainStyledAttributes(view.attrs, R.styleable.FancyButton)

        initialText = attrs.getString(R.styleable.FancyButton_initialText) as String
        loadingText = attrs.getString(R.styleable.FancyButton_loadingText) as String
        initialTextColor = attrs.getColor(R.styleable.FancyButton_initialTextColor, Color.WHITE)
        loadingTextColor = attrs.getColor(R.styleable.FancyButton_loadingTextColor, Color.WHITE)
        bgRectColor = attrs.getColor(R.styleable.FancyButton_bgRectColor, Color.BLACK)
        loadingColor = attrs.getColor(R.styleable.FancyButton_loadingColor, Color.BLACK)
        successColor = attrs.getColor(R.styleable.FancyButton_successColor, Color.BLACK)
        errorColor = attrs.getColor(R.styleable.FancyButton_errorColor, Color.BLACK)
        sweepAngle = attrs.getFloat(R.styleable.FancyButton_sweepAngle, 0f)
        roundRectRadius = attrs.getFloat(R.styleable.FancyButton_rectCornerRadius, 0f)

        attrs.recycle()
    }
}