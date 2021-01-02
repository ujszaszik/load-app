package com.udacity.loadapp.fetcher.reposelector

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.udacity.loadapp.R

class RepoSelectorRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatRadioButton(context, attrs) {

    private var attributes: TypedArray =
        context.obtainStyledAttributes(attrs, R.styleable.RepoSelectorRadioButton)

    internal lateinit var name: String
    internal lateinit var url: String
    var isCustomFormat: Boolean = false

    init {
        initializeAttributes()
        if (!isCustomFormat) text = name
        setListenerForEnteringCustomURL()
    }

    private fun initializeAttributes() {
        name = attributes.getString(R.styleable.RepoSelectorRadioButton_name).toString()
        url = attributes.getString(R.styleable.RepoSelectorRadioButton_url).toString()
        isCustomFormat =
            attributes.getBoolean(R.styleable.RepoSelectorRadioButton_isCustomFormat, false)

    }

    private fun setListenerForEnteringCustomURL() {
        if (isCustomFormat) {
            setOnClickListener { CustomLinkDialogBuilder(this).buildDialog() }
        }
    }

}