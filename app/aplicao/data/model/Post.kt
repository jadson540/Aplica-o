package com.example.aplicao.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("{word}")
    suspend fun getPostById(
        @Path("word") word: String
    ): List<ApiResponse>
}
