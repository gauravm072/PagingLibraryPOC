package com.example.paginglibrarypoc.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginglibrarypoc.api.NetworkApi
import com.example.paginglibrarypoc.model.ModelClass
private const val FIRST_PAGE = 1
class MyPagingSource(private val api: NetworkApi, private val userName: String) : PagingSource<Int, ModelClass>() {

    init {
        Log.d("MyPagingSource ", ": init block is called")
    }
    override fun getRefreshKey(state: PagingState<Int, ModelClass>): Int? {
        Log.d("PAGINGGGG: ", "getRefreshKey: ${state.anchorPosition}")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ModelClass> {
        return try {
            Log.d("PAGINGGGG: ", "load: ${params.key}")

            val page = params.key ?: FIRST_PAGE
            val response = api.fetchRepos(userName, page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}