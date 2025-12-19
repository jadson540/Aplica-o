package com.example.aplicao.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicao.data.model.Post
import com.example.aplicao.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostListViewModel(
    private val repository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _searchResult = MutableStateFlow<Post?>(null)
    val searchResult: StateFlow<Post?> = _searchResult

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = UiState.Success(repository.getPosts())
        }
    }

    fun searchWord(word: String) {
        viewModelScope.launch {
            _searchResult.value = repository.searchWord(word)
        }
    }

    fun clearSearch() {
        _searchResult.value = null
    }
}
