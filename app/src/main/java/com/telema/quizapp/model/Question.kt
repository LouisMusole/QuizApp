package com.telema.quizapp.model

data class Question(
    val orderNumber: Int =0,
    val titled: String = "",
    val format: String = "",
    val suggestedResponses : List<String> = emptyList(),
    val response : List<String> = emptyList()
)
