package com.example.commerce.domain

import com.example.commerce.data.domain.ItemDomain
import com.example.commerce.data.domain.toDomain
import com.example.commerce.data.repository.ItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository,
    private val dispatcher: CoroutineDispatcher
) {
    fun getItems(): Flow<UiState<List<ItemDomain>>> = flow {
        emit(UiState.LOADING)
        try {
            val result = repository.callItems(body = 1)
            if (result.isSuccessful) {
                result.body()?.data?.let {
                    val items = it.toDomain()
                    emit(UiState.SUCCESS(items))

                }
                    ?: throw Exception("Items are empty")
            } else throw Exception(result.errorBody()?.toString())
        } catch (e: Exception) {
            emit(UiState.ERROR(e))
        }
    }.flowOn(dispatcher)
}