package com.udacity.loadapp.detail

import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.udacity.loadapp.R
import com.udacity.loadapp.databinding.ActivityDetailBinding
import com.udacity.loadapp.fetcher.FetcherActivity
import com.udacity.loadapp.network.FileModel
import com.udacity.loadapp.repolist.RepoListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.okButton.setOnClickListener { navigateToMainScreen() }
        binding.listButton.setOnClickListener { navigateToRepoList() }

        intent.bindFile()
        setActionBarText()
        cancelNotification()
        startDetailViewEnterAnimation()
    }


    private fun Intent.bindFile() {
        getParcelableExtra<FileModel>(getString(
                R.string.file_model_intent_id
            )).also {
            it?.let { binding.repoDetailView.bindFile(it) }
        }
    }

    private fun setActionBarText() {
        supportActionBar?.title = getString(R.string.detail_view_name)
    }

    private fun navigateToMainScreen() {
        binding.detailActivityMotionLayout.transitionToStart()
        binding.repoDetailView.onDismiss()
        startMainPageAfterTransitionFinished()
    }

    private fun startMainPageAfterTransitionFinished() {
        GlobalScope.launch {
            delay(resources.getInteger(R.integer.transition_default_duration).toLong())
            navigateToMainPage()
        }
    }

    private fun navigateToMainPage() {
        Intent(this, FetcherActivity::class.java).run {
            startActivity(this)
        }
    }

    private fun navigateToRepoList() {
        Intent(this, RepoListActivity::class.java).run {
            startActivity(this)
        }
    }

    private fun cancelNotification() {
        notificationManager.cancel(getString(R.string.notification_id).toInt())
    }

    private fun startDetailViewEnterAnimation() {
        binding.repoDetailView.onLoad()
    }


}