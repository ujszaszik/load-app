package com.udacity.loadapp.fetcher

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.udacity.loadapp.R
import com.udacity.loadapp.databinding.ActivityFetcherBinding
import com.udacity.loadapp.fetcher.fancybutton.showToast
import com.udacity.loadapp.notification.DownloadNotificator
import com.udacity.loadapp.fetcher.reposelector.RepoSelection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FetcherActivity : AppCompatActivity() {

    private val viewModel by viewModels<FetcherViewModel>()
    private lateinit var binding: ActivityFetcherBinding

    @Inject
    lateinit var notificator: DownloadNotificator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fetcher)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.fancyButton.setOnClickListener {
            handleButtonClick(binding.repoSelector.getSelectedRepo())
        }

        setActionBarText()
        requestPermissionIfNotAlreadyGranted()
        observeDownloadState()
    }

    private fun requestPermissionIfNotAlreadyGranted() {
        if (!PermissionUtil.isPermitted) {
            PermissionUtil.requestPermission(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtil.onRequestPermissionsResult(
            requestCode,
            grantResults
        )
    }

    private fun observeDownloadState() {
        viewModel.downloadState.observe(this, Observer {
            viewModel.downloadedFile.value?.let { file ->
                if (file.status.isFinished()) notificator.sendNotification(file)
            }
        })
    }

    private fun handleButtonClick(selectedRepo: RepoSelection) {
        if (selectedRepo == RepoSelection.EMPTY) showToast(getString(R.string.toast_no_file_selected))
        else viewModel.download(selectedRepo)
    }

    private fun setActionBarText() {
        supportActionBar?.title = getString(R.string.app_name)
    }

}