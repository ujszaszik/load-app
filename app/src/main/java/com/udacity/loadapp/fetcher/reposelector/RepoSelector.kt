package com.udacity.loadapp.fetcher.reposelector

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.udacity.loadapp.R
import com.udacity.loadapp.databinding.RepoSelectorBinding

class RepoSelector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var binding: RepoSelectorBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = RepoSelectorBinding.inflate(inflater, this, true)
    }

    fun getSelectedRepo(): RepoSelection {
        return when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radio1 -> RepoSelection(binding.radio1.name, binding.radio1.url)
            R.id.radio2 -> RepoSelection(binding.radio2.name, binding.radio2.url)
            R.id.radio3 -> RepoSelection(binding.radio3.name, binding.radio3.url)
            R.id.radio4 -> RepoSelection(binding.radio4.name, binding.radio4.url)
            else -> RepoSelection.EMPTY
        }
    }
}