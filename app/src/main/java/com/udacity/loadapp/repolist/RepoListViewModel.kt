package com.udacity.loadapp.repolist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.loadapp.network.FileModel
import com.udacity.loadapp.download.DownloadRepository

class RepoListViewModel @ViewModelInject constructor(
    private val downloadRepository: DownloadRepository
) : ViewModel() {

    val listOfDownloads = downloadRepository.listOfDownloads

    private val _itemToOpen = MutableLiveData<FileModel>()
    val itemToOpen: LiveData<FileModel> get() = _itemToOpen

    fun onItemClicked(fileModel: FileModel) {
        _itemToOpen.postValue(fileModel)
    }

    fun resetClickedItem() {
        _itemToOpen.value = null
    }
}