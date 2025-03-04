package com.example.commerce.data.repository

import com.example.commerce.data.model.BaseResponse
import com.example.commerce.data.model.ItemModel
import com.example.commerce.data.service.ItemService
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val service: ItemService
): ItemRepository
{
    override suspend fun callItems(body: Int): BaseResponse<ItemModel> {
        return service.callItemsApi(body)
    }
}