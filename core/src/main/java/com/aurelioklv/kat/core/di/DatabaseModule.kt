package com.aurelioklv.kat.core.di

import android.content.Context
import androidx.room.Room
import com.aurelioklv.kat.core.data.local.dao.BreedDao
import com.aurelioklv.kat.core.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "kat.db"
        ).build()
    }

    @Provides
    fun provideBreedDao(database: AppDatabase): BreedDao = database.breedDao()
}