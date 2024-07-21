package com.aurelioklv.kat.core.data.remote

import android.util.Log
import com.aurelioklv.kat.core.data.remote.api.ApiResponse
import com.aurelioklv.kat.core.data.remote.api.ApiService
import com.aurelioklv.kat.core.data.remote.response.NetworkBreed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getAllBreed(): Flow<ApiResponse<List<NetworkBreed>>> {
        return flow {
            try {
                val response = apiService.getBreeds(0, 100)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

