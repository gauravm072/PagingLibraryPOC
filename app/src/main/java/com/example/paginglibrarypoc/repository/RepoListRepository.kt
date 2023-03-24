package com.example.paginglibrarypoc.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paginglibrarypoc.api.NetworkApi

class RepoListRepository(private val api: NetworkApi) {
    fun fetchRepos(userName: String) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MyPagingSource(api, userName) }
    ).flow
}