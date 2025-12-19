package com.example.aplicao.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicao.data.model.Post
import com.example.aplicao.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val repository: PostRepository
) : ViewModel() {

    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> = _post

    fun loadById(id: Int) {
        viewModelScope.launch {
            _post.value = repository.getPostById(id)
        }
    }

    fun loadByWord(word: String) {
        viewModelScope.launch {
            _post.value = repository.searchWord(word)
        }
    }
}
