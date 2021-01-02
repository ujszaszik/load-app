package com.udacity.loadapp.repolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.loadapp.network.FileModel
import com.udacity.loadapp.databinding.RepoListRecyclerRowBinding


class RepoListViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        private lateinit var binding: RepoListRecyclerRowBinding

        fun from(parent: ViewGroup): RepoListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            binding = RepoListRecyclerRowBinding.inflate(inflater, parent, false)
            return RepoListViewHolder(binding.root)
        }
    }

    fun bind(file: FileModel, listener: ItemClickListener) {
        binding.file = file
        binding.listener = listener
        binding.executePendingBindings()
    }

}