package com.aurelioklv.kat.core.data.remote.api

import com.aurelioklv.kat.core.data.remote.response.NetworkBreed
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("breeds/")
    suspend fun getBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<NetworkBreed>

    @GET("breeds/{breedId}")
    suspend fun getBreedById(
        @Path("breedId") breedId: String,
    ): NetworkBreed
}