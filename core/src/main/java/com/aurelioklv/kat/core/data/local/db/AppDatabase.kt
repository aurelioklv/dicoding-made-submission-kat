package com.aurelioklv.kat.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aurelioklv.kat.core.data.local.dao.BreedDao
import com.aurelioklv.kat.core.data.local.entity.BreedEntity

@Database(
    entities = [BreedEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
}