package com.udacity.loadapp.notification.icon

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

fun Drawable.toDrawnBitmap(bitmap: Bitmap): Bitmap {
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height);
    draw(canvas);
    return bitmap;
}