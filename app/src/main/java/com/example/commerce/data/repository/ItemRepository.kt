package com.example.commerce.data.repository

import com.example.commerce.data.model.BaseResponse
import com.example.commerce.data.model.ItemModel
import retrofit2.Response


interface ItemRepository {
    suspend fun callItems(body: Int): Response<BaseResponse<ItemModel>>
}