package com.udacity.loadapp.download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.loadapp.network.FileModel
import com.udacity.loadapp.database.FileDatabase
import com.udacity.loadapp.database.FileEntity
import com.udacity.loadapp.download.writer.FileWriter
import com.udacity.loadapp.download.writer.FileWriterFactory
import com.udacity.loadapp.network.IO
import com.udacity.loadapp.network.NetworkUtil
import com.udacity.loadapp.network.isValid
import com.udacity.loadapp.fetcher.reposelector.RepoSelection
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject


class DownloadRepository @Inject constructor(
    fileWriterFactory: FileWriterFactory,
    private val fileDatabase: FileDatabase
) {

    private val _state = MutableLiveData<DownloadState>()
    val state: LiveData<DownloadState> get() = _state

    private val _downloadedFile = MutableLiveData<FileModel>()
    val downloadedFile: LiveData<FileModel> get() = _downloadedFile

    private val listOfDownloadEntities: LiveData<List<FileEntity>>
        get() = fileDatabase.fileDao().listAllFiles()
    val listOfDownloads =
        Transformations.map(listOfDownloadEntities) { list -> list.map { it.toModel() } }

    private var fileWriter: FileWriter = fileWriterFactory.get()
    private var selectedRepo = RepoSelection.EMPTY

    suspend fun handleFileRequest(selectedRepo: RepoSelection) {
        this.selectedRepo = selectedRepo
        IO { download() }
    }

    private fun download() {
        if (!NetworkUtil.isConnected) {
            _state.postValue(DownloadState.Disconnected)
        } else if (!isValidUrl()) {
            _state.postValue(DownloadState.NonExisting)
        } else if (isFileAlreadyInserted()) {
            _state.postValue(DownloadState.AlreadyDownloaded)
        } else {
            downloadFile()
        }
        onDownloadFinished()
    }

    private fun downloadFile() {
        _state.postValue(DownloadState.Loading)
        try {
            val fileSize: Long = saveFile()
            persistToDatabase(fileSize, DownloadState.Success)
            _state.postValue(DownloadState.Success)
        } catch (t: Throwable) {
            t.printStackTrace()
            persistToDatabase(0L, DownloadState.Error)
            _state.postValue(DownloadState.Error)
        }
    }

    private fun saveFile(): Long {
        URL(selectedRepo.getFullUrl()).openStream().use { input ->
            return fileWriter.write(input, selectedRepo.name)
        }
    }

    private fun persistToDatabase(fileSize: Long, state: DownloadState) {
        FileEntity.create(selectedRepo.name, fileSize, state).run {
            fileDatabase.fileDao().insertFile(this)
            _downloadedFile.postValue(toModel())
        }
    }

    private fun isFileAlreadyInserted(): Boolean {
        return fileDatabase.fileDao().getFileAmountStoredWithThisName(selectedRepo.name) != 0
    }

    private fun isValidUrl(): Boolean {
        val url = URL(selectedRepo.getFullUrl())
        val urlConnection = url.openConnection() as HttpURLConnection
        return urlConnection.isValid()
    }

    private fun onDownloadFinished() {
        selectedRepo = RepoSelection.EMPTY
    }
}