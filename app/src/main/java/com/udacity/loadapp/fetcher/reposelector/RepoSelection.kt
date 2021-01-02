package com.udacity.loadapp.fetcher.reposelector

data class RepoSelection(
    val name: String,
    val url: String
) {

    companion object {
        val EMPTY: RepoSelection = RepoSelection("", "")
        private const val REPO_SUFFIX = "/archive/master.zip"
    }

    fun getFullUrl(): String = url + REPO_SUFFIX
}