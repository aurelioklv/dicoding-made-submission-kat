package com.aurelioklv.kat.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_image")
data class CatImageEntity(
    @PrimaryKey val id: String,
    val url: String,
    val width: Int,
    val height: Int,
)