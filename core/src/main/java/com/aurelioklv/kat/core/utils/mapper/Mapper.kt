package com.aurelioklv.kat.core.utils.mapper

interface Mapper<in T, out U> {
    fun map(from: T): U
}