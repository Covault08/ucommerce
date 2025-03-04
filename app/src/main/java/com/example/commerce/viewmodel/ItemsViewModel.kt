package com.example.commerce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerce.data.domain.ItemDomain
import com.example.commerce.domain.GetItemsUseCase
import com.example.commerce.domain.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getNewsUseCase: GetItemsUseCase,
    private val dispatcher: CoroutineDispatcher
): ViewModel(){
    private val _itemsState: MutableStateFlow<UiState<List<ItemDomain>>> = MutableStateFlow(UiState.LOADING)
    val itemState = _itemsState.asStateFlow()


    init {
        viewModelScope.launch(dispatcher) {
            getItems()
        }
    }

    private suspend fun getItems(){
        getNewsUseCase.getItems().collect{
            _itemsState.value = it
        }
    }

}