package com.udacity.loadapp.repolist

import com.udacity.loadapp.network.FileModel


class ItemClickListener(private val listener: (FileModel) -> Unit) {

    fun performClick(fileModel: FileModel) {
        listener.invoke(fileModel)
    }
}