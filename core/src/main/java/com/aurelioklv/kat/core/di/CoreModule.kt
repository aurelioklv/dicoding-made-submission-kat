package com.aurelioklv.kat.core.di

import androidx.room.Room
import com.aurelioklv.kat.core.BuildConfig
import com.aurelioklv.kat.core.data.BreedRepository
import com.aurelioklv.kat.core.data.local.LocalDataSource
import com.aurelioklv.kat.core.data.local.db.AppDatabase
import com.aurelioklv.kat.core.data.local.preferences.UserPreferences
import com.aurelioklv.kat.core.data.remote.RemoteDataSource
import com.aurelioklv.kat.core.data.remote.api.ApiService
import com.aurelioklv.kat.core.domain.repository.IBreedRepository
import com.aurelioklv.kat.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSPHRASE.toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "kat.db")
            .openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(BuildConfig.HOSTNAME, BuildConfig.PIN)
            .build()
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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

val dataStoreModule = module {
    single { UserPreferences(androidContext()) }
}