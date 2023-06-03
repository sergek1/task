package com.example.shoppinglist.data.db

import androidx.room.*
import com.example.shoppinglist.data.db.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem) // чтобы вызывать в доп потоке асинхронно

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems() : Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_items ORDER BY item_name")
    fun getAllSortedByName() : Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_items ORDER BY shop_name")
    fun getAllSortedByShop() : Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_items ORDER BY item_name LIMIT 3")
    fun getTopThreeByName() : Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_items ORDER BY item_amount LIMIT 3")
    fun getTopThreeByAmount() : Flow<List<ShoppingItem>>

}