package com.udacity.loadapp.fetcher.fancybutton

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.udacity.loadapp.R
import com.udacity.loadapp.fetcher.fancybutton.params.ButtonParams
import com.udacity.loadapp.fetcher.fancybutton.states.ButtonState
import com.udacity.loadapp.fetcher.fancybutton.states.finished.DownloadFinishedBackgroundDrawer
import com.udacity.loadapp.fetcher.fancybutton.states.finished.DownloadFinishedColorAnimator
import com.udacity.loadapp.fetcher.fancybutton.states.finished.DownloadFinishedIconDrawer
import com.udacity.loadapp.fetcher.fancybutton.states.finished.DownloadFinishedTransitionAnimator
import com.udacity.loadapp.fetcher.fancybutton.states.initial.InitialStateDrawer
import com.udacity.loadapp.fetcher.fancybutton.states.loading.CircleAnimator
import com.udacity.loadapp.fetcher.fancybutton.states.loading.LoadingStateDrawer
import com.udacity.loadapp.fetcher.fancybutton.states.loading.ProgressAnimator


class FancyButton @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null
) : View(context, attrs) {

    private var buttonState: ButtonState = ButtonState.Initial

    internal var params: ButtonParams = ButtonParams(this)

    private val progressAnimator: ProgressAnimator = ProgressAnimator(this)
    private val circleAnimator: CircleAnimator = CircleAnimator(this)
    private val downloadFinishedTransitionAnimator: DownloadFinishedTransitionAnimator =
        DownloadFinishedTransitionAnimator(this)
    private val downloadFinishedColorAnimator: DownloadFinishedColorAnimator =
        DownloadFinishedColorAnimator(this)

    internal var isDownloadSuccessful = true

    override fun onDraw(canvas: Canvas) {
        initBackground()
        selectDrawType(canvas)
    }

    private fun initBackground() {
        if (!params.isBgRectInitialized()) params.bgRect = rectFilledOnView(this)
    }

    private fun selectDrawType(canvas: Canvas) = when (buttonState) {
        ButtonState.Initial -> InitialStateDrawer.draw(this, canvas)
        ButtonState.Loading -> LoadingStateDrawer.draw(this, canvas)
        ButtonState.Finished -> DownloadFinishedBackgroundDrawer.draw(this, canvas)
        ButtonState.ShowIcon -> DownloadFinishedIconDrawer.draw(this, canvas)
    }

    override fun performClick(): Boolean {
        if (!super.performClick()) startDownload()
        return true
    }

    fun startDownload() {
        buttonState = ButtonState.Loading
        isEnabled = false
        progressAnimator.start()
        circleAnimator.start()
    }

    fun onDownloadFinished(success: Boolean) {
        buttonState = ButtonState.Finished
        isDownloadSuccessful = success
        invalidate()
        downloadFinishedTransitionAnimator.start()
        downloadFinishedColorAnimator.start()
    }

    fun onAnimationFinished() {
        buttonState = ButtonState.ShowIcon
    }

    fun onDisconnected() {
        buttonState = ButtonState.Initial
        showToast(context.getString(R.string.toast_no_connection))
    }

    fun onNonExistingFile() {
        buttonState = ButtonState.Initial
        showToast(context.getString(R.string.toast_file_not_exists))
    }

    fun onAlreadyDownloaded() {
        buttonState = ButtonState.Initial
        showToast(context.getString(R.string.toast_file_already_downloaded))
    }

}