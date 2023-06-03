package com.example.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    val shoppingItems = repository.getAllShoppingItems().asLiveDataFlow()

    fun upsert(item: ShoppingItem) = viewModelScope.launch { // dispatcher.main -
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = viewModelScope.launch { // dispatcher.main -
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

    fun <T> Flow<T>.asLiveDataFlow() = shareIn(viewModelScope, SharingStarted.Eagerly, 1)
}