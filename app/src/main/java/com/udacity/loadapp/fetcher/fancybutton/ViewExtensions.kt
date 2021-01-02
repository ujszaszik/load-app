package com.udacity.loadapp.fetcher.fancybutton

import android.app.Activity
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Number.half() = this.toFloat() / 2

fun View.verticalCenter() = height.half()

fun View.horizontalCenter() = width.half()

fun Rect.verticalCenter() = height().half()

fun Rect.horizontalCenter() = width().half()

fun rectStub(): Rect = Rect(0, 0, 0, 0)

fun rectFilledOnView(view: View): Rect = Rect(0, 0, view.width, view.height)

fun rectByLayoutParamsOf(view: View): Rect =
    Rect(0, 0, view.layoutParams.width, view.layoutParams.height)

fun View.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.getDrawable(id: Int): Drawable? {
    return ContextCompat.getDrawable(context, id)
}