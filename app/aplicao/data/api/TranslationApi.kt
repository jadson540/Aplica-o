package com.example.aplicao.data.api

import retrofit2.http.GET
import retrofit2.http.Query

data class TranslationResponse(
    val responseData: ResponseData
)

data class ResponseData(
    val translatedText: String
)

interface TranslationApi {

    @GET("get")
    suspend fun translate(
        @Query("q") text: String,
        @Query("langpair") langPair: String = "en|pt"
    ): TranslationResponse
}
