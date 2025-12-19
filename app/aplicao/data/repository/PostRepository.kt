package com.example.aplicao.data.repository

import com.example.aplicao.data.api.PostApi
import com.example.aplicao.data.api.TranslationApi
import com.example.aplicao.data.model.Post

class PostRepository(
    private val dictionaryApi: PostApi,
    private val translationApi: TranslationApi
) {

    private val palavras = listOf(
        "house",
        "book",
        "love",
        "time",
        "life"
    )

    fun getPosts(): List<Post> {
        return palavras.mapIndexed { index, palavra ->
            Post(
                id = index,
                title = palavra,
                body = ""
            )
        }
    }

    suspend fun getPostById(id: Int): Post {
        return searchWord(palavras[id], id)
    }

    suspend fun searchWord(word: String, id: Int = 0): Post {
        return try {
            val response = dictionaryApi.getPostById(word.lowercase())

            val definitionEn = response
                .firstOrNull()
                ?.meanings
                ?.firstOrNull()
                ?.definitions
                ?.firstOrNull()
                ?.definition
                ?: "Definition not found."

            val translation = translationApi.translate(definitionEn)

            Post(
                id = id,
                title = word,
                body = translation.responseData.translatedText
            )
        } catch (e: Exception) {
            Post(
                id = id,
                title = word,
                body = "Não foi possível encontrar a definição."
            )
        }
    }
}
