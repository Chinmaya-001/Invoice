package com.example.invoice.dataManager

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemListDao {

    @Query("SELECT * FROM item_table")
    fun getAllItemList(): LiveData<List<ItemListData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemList(itemListDao: ItemListDao)

}