package com.example.paginglibrarypoc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.paginglibrarypoc.api.RetrofitClient
import com.example.paginglibrarypoc.model.ModelClass
import com.example.paginglibrarypoc.repository.RepoListRepository
import kotlinx.coroutines.flow.Flow

class RepositoryViewModel: ViewModel() {

    private var repository: RepoListRepository? = RepoListRepository(RetrofitClient.getNetworkApi())
    private var currentUserName: String? = null
    var currentSearchResult: LiveData<PagingData<ModelClass>>? = null

    fun searchRepos(username: String): LiveData<PagingData<ModelClass>>? {
        val lastResult = currentSearchResult
        if (username == currentUserName && lastResult != null) {
            return lastResult
        }
        currentUserName = username
        val newResult = repository?.fetchRepos(username)
        currentSearchResult = newResult
        return newResult
    }
}