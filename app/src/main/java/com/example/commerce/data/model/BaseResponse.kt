package com.example.commerce.data.model

data class BaseResponse<T>(
    val `data`: List<T>,
    val message: String,
    val status: Boolean
)