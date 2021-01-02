package com.udacity.loadapp.fetcher.reposelector

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import com.udacity.loadapp.R
import com.udacity.loadapp.databinding.CustomRepoSelectorLayoutBinding
import com.udacity.loadapp.fetcher.fancybutton.showToast

class CustomLinkDialogBuilder(private val view: RepoSelectorRadioButton) {

    private lateinit var dialogLayoutBinding: CustomRepoSelectorLayoutBinding

    internal fun buildDialog() {
        val dialog: AlertDialog = AlertDialog.Builder(view.context)
            .setTitle(getString(R.string.dialog_title))
            .setView(getView())
            .setPositiveButton(getString(R.string.label_button_ok)) { dialogInterface, _ ->
                onPositiveButtonClicked(dialogInterface)
            }
            .setNegativeButton(getString(R.string.label_button_cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }.create()

        dialog.setOnShowListener { dialogInterface ->
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                onPositiveButtonClicked(dialogInterface)
            }
        }
        dialog.show()
    }

    private fun onPositiveButtonClicked(dialogInterface: DialogInterface) {
        if (isCustomNameEmpty() || isCustomUrlEmpty()) {
            view.showToast(getString(R.string.toast_no_file_selected))
        } else {
            setAttributes()
            dialogInterface.dismiss()
        }
    }

    private fun isCustomNameEmpty(): Boolean {
        return dialogLayoutBinding.repoNameEditText.text.toString().isEmpty()
    }

    private fun isCustomUrlEmpty(): Boolean {
        return dialogLayoutBinding.repoUrlEditText.text.toString().isEmpty()
    }

    private fun setAttributes() {
        view.name = dialogLayoutBinding.repoNameEditText.text.toString()
        view.url = dialogLayoutBinding.repoUrlEditText.text.toString()
        view.text = view.name
    }

    private fun getView(): View {
        val inflater = LayoutInflater.from(view.context)
        dialogLayoutBinding = CustomRepoSelectorLayoutBinding.inflate(inflater)
        return dialogLayoutBinding.root
    }

    private fun getString(id: Int): String {
        return view.context.getString(id)
    }
}