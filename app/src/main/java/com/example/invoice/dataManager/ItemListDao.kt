package com.example.invoice.dataManager

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemListDao {

    @Query("SELECT * FROM item_table")
    fun getAllItemList(): Flow<List<ItemListData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(itemListData: ItemListData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQty(itemListData: ItemListData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPrice(itemListData: ItemListData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAmount(itemListData: ItemListData)

    @Upsert
    suspend fun updateItemNumber(itemListData: ItemListData)

}