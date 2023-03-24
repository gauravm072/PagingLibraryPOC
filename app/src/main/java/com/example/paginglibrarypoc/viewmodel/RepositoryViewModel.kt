package com.example.paginglibrarypoc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.paginglibrarypoc.api.RetrofitClient
import com.example.paginglibrarypoc.model.ModelClass
import com.example.paginglibrarypoc.repository.RepoListRepository
import kotlinx.coroutines.flow.Flow

class RepositoryViewModel: ViewModel() {

    private var repository: RepoListRepository? = RepoListRepository(RetrofitClient.getNetworkApi())
    private var currentUserName: String? = null
    private var currentSearchResult: Flow<PagingData<ModelClass>>? = null

    fun searchRepos(username: String): Flow<PagingData<ModelClass>>? {
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