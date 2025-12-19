package com.example.aplicao.data.api

data class ApiResponse(
    val word: String,
    val meanings: List<Meaning>
)

data class Meaning(
    val definitions: List<Definition>
)

data class Definition(
    val definition: String
)
