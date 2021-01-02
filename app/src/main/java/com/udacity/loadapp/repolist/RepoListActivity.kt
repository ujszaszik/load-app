package com.udacity.loadapp.repolist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.loadapp.R
import com.udacity.loadapp.databinding.ActivityRepoListBinding
import com.udacity.loadapp.detail.DetailActivity
import com.udacity.loadapp.detail.RepoDetailView
import com.udacity.loadapp.fetcher.FetcherActivity
import com.udacity.loadapp.network.FileModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoListBinding

    private val viewModel by viewModels<RepoListViewModel>()

    private lateinit var listener: ItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list)

        val manager = LinearLayoutManager(this)
        binding.repoListRecyclerView.layoutManager = manager

        binding.lifecycleOwner = this

        setActionBarText()

        listener = ItemClickListener { viewModel.onItemClicked(it) }

        binding.backToMainPageButton.setOnClickListener { onBackPressed() }
        observeRepoList()
        observeItemClicked()
    }

    private fun setActionBarText() {
        supportActionBar?.title = getString(R.string.repo_list_name)
    }

    private fun observeRepoList() {
        viewModel.listOfDownloads.observe(this, Observer {
            binding.repoListRecyclerView.adapter =
                RepoListAdapter(listener).apply {
                    submitList(it)
                }
        })
    }

    private fun observeItemClicked() {
        viewModel.itemToOpen.observe(this, Observer {
            it?.let {
                navigateToFileDetails(it)
                viewModel.resetClickedItem()
            }
        })
    }

    private fun navigateToFileDetails(file: FileModel) {
        Intent(this, DetailActivity::class.java).apply {
            putExtra(getString(R.string.file_model_intent_id), file)
        }.also {
            startActivity(it)
        }
    }

    override fun onBackPressed() {
        binding.repoListMotionLayout.transitionToStart()
        showMainScreenAfterExitAnimationFinished()
    }

    private fun showMainScreenAfterExitAnimationFinished() {
        GlobalScope.launch {
            delay(RepoDetailView.DURATION)
            navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() {
        Intent(this, FetcherActivity::class.java).run {
            startActivity(this)
        }
    }
}