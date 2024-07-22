package com.aurelioklv.kat.core.di

import androidx.room.Room
import com.aurelioklv.kat.core.BuildConfig
import com.aurelioklv.kat.core.data.BreedRepository
import com.aurelioklv.kat.core.data.local.LocalDataSource
import com.aurelioklv.kat.core.data.local.db.AppDatabase
import com.aurelioklv.kat.core.data.remote.RemoteDataSource
import com.aurelioklv.kat.core.data.remote.api.ApiService
import com.aurelioklv.kat.core.domain.repository.IBreedRepository
import com.aurelioklv.kat.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<AppDatabase>().breedDao() }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "kat.db")
            .fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IBreedRepository> { BreedRepository(get(), get(), get()) }
}