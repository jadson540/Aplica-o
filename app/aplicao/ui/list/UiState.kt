package com.example.aplicao.ui.list

import com.example.aplicao.data.model.Post

sealed class UiState {
    object Loading : UiState()
    data class Success(val posts: List<Post>) : UiState()
    data class Error(val message: String) : UiState()
}
