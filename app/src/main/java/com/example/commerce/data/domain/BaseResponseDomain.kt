package com.example.commerce.data.domain



data class BaseResponse<T>(
    val `data`: List<T>,
    val message: String,
    val status: Boolean
)
