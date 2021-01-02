package com.udacity.loadapp.fetcher.fancybutton

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface

object Painters {

    fun getBackgroundPaint(): Paint {
        return Paint().apply {
            isAntiAlias = true
            strokeWidth = 12.0f
            color = Color.GREEN
        }
    }

    fun getErrorBackgroundPaint(): Paint {
        return Paint().apply {
            isAntiAlias = true
            strokeWidth = 12.0f
            color = Color.RED
        }
    }

    fun getCirclePaint(): Paint {
        return Paint().apply {
            isAntiAlias = true
            strokeWidth = 12.0f
            color = Color.YELLOW
        }
    }

    fun getProgressPaint(): Paint {
        return Paint().apply {
            isAntiAlias = true
            strokeWidth = 12.0f
            color = Color.BLUE
        }
    }

    fun getTextPaint(): Paint {
        return Paint().apply {
            color = Color.BLACK
            textSize = 42f
            typeface = Typeface.MONOSPACE
            textAlign = Paint.Align.CENTER
        }
    }
}