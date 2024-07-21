package com.aurelioklv.kat.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class NetworkCatImage(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("height")
    val height: Int? = null,

    @field:SerializedName("breeds")
    val breeds: List<NetworkBreed?>? = null,
)