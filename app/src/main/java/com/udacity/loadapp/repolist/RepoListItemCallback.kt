package com.udacity.loadapp.repolist

import androidx.recyclerview.widget.DiffUtil
import com.udacity.loadapp.network.FileModel

object RepoListItemCallback : DiffUtil.ItemCallback<FileModel>() {
    override fun areItemsTheSame(oldItem: FileModel, newItem: FileModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: FileModel, newItem: FileModel): Boolean {
        return oldItem == newItem
    }
}