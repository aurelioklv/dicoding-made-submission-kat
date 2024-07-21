package com.aurelioklv.kat.core.data.remote.api

import com.aurelioklv.kat.core.data.remote.response.NetworkBreed
import com.aurelioklv.kat.core.data.remote.response.NetworkCatImage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("images/search")
    suspend fun searchImage(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("size") size: String,
        @Query("mime_types") mimeType: String,
        @Query("order") order: String,
        @Query("has_breeds") hasBreed: Boolean,
    ): List<NetworkCatImage>

    @GET("images/:imageId")
    suspend fun getImageById(
        @Path("imageId") imageId: String,
    ): NetworkCatImage

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