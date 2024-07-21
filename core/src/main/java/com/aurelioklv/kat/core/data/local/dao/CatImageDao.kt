package com.aurelioklv.kat.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aurelioklv.kat.core.data.local.entity.CatImageEntity

@Dao
interface CatImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatImage(catImages: List<CatImageEntity>)

    @Query("SELECT * FROM cat_image")
    fun getAllCatImages(): List<CatImageEntity>

    @Query("DELETE FROM cat_image")
    suspend fun deleteAll()
}