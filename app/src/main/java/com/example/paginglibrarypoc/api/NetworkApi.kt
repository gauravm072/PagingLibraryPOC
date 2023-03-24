package com.example.paginglibrarypoc.api

import com.example.paginglibrarypoc.model.ModelClass
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {
    @GET("users/{username}/repos")
    suspend fun fetchRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): List<ModelClass>
}