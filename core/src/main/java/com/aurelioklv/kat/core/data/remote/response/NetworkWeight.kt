package com.aurelioklv.kat.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class NetworkWeight(

    @field:SerializedName("metric")
    val metric: String? = null,

    @field:SerializedName("imperial")
    val imperial: String? = null,
)
