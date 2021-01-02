package com.udacity.loadapp.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.udacity.loadapp.databinding.RepoDetailViewBinding
import com.udacity.loadapp.network.FileModel

class RepoDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var binding: RepoDetailViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = RepoDetailViewBinding.inflate(inflater, this, true)
    }

    companion object {
        private const val TRANSPARENT = 0f
        private const val VISIBLE = 1.0F
        const val DURATION = 1250L
    }

    fun bindFile(file: FileModel) {
        binding.file = file
    }

    fun onLoad() {
        alpha = TRANSPARENT
        animate().alpha(VISIBLE).duration = DURATION
    }

    fun onDismiss() {
        alpha = VISIBLE
        animate().alpha(TRANSPARENT).duration = DURATION
    }
}