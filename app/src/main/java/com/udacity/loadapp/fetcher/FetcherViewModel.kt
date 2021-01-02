package com.udacity.loadapp.fetcher

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.loadapp.download.DownloadRepository
import com.udacity.loadapp.fetcher.reposelector.RepoSelection
import kotlinx.coroutines.launch

class FetcherViewModel @ViewModelInject constructor(
    private val downloadRepository: DownloadRepository
) : ViewModel() {

    val downloadState = downloadRepository.state

    val downloadedFile = downloadRepository.downloadedFile

    fun download(selectedRepo: RepoSelection) {
        viewModelScope.launch {
            downloadRepository.handleFileRequest(selectedRepo)
        }
    }
}