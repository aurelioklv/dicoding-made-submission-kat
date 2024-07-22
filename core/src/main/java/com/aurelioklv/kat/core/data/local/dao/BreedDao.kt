package com.aurelioklv.kat.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aurelioklv.kat.core.data.local.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreed(breeds: List<BreedEntity>)

    @Query("SELECT * FROM breed")
    fun getAllBreed(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breed WHERE is_favorite = 1")
    fun getFavoriteBreed(): Flow<List<BreedEntity>>

    @Update
    fun updateFavoriteBreed(breed: BreedEntity)

    @Query("SELECT * FROM breed WHERE id = :id")
    fun getBreedById(id: String): Flow<BreedEntity>
}