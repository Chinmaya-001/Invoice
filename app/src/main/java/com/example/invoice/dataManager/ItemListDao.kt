package com.example.invoice.dataManager

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemListDao {

    @Query("SELECT * FROM item_table")
    suspend fun getAllItemList(): Flow<List<ItemListData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemList(itemListData: ItemListData)

}