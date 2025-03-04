package com.example.commerce.data.service

import com.example.commerce.data.BODY
import com.example.commerce.data.ENDPOINT
import com.example.commerce.data.model.BaseResponse
import com.example.commerce.data.model.ItemModel
import retrofit2.http.POST
import retrofit2.http.Query

interface ItemService {

    @POST(ENDPOINT)
    suspend fun callItemsApi(
        @Query(BODY) body: Int,
    ): BaseResponse<ItemModel>
}