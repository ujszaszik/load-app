package com.udacity.loadapp.repolist

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.udacity.loadapp.network.FileModel

class RepoListAdapter(private val listener: ItemClickListener) :
    ListAdapter<FileModel, RepoListViewHolder>(RepoListItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        return RepoListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}