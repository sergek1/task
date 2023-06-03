package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    private val dao get() = db.getShoppingDao()

    suspend fun upsert(item: ShoppingItem) = dao.upsert(item)

    suspend fun delete(item: ShoppingItem) = dao.delete(item)

    fun getAllShoppingItems(): Flow<List<ShoppingItem>> = dao.getAllShoppingItems()
}