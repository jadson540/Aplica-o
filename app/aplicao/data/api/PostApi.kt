package com.example.aplicao.data.model

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int = 0
)
