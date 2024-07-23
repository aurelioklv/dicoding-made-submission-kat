package com.aurelioklv.kat

import android.app.Application
import com.aurelioklv.kat.core.data.local.preferences.ThemeManager
import com.aurelioklv.kat.core.di.dataStoreModule
import com.aurelioklv.kat.core.di.databaseModule
import com.aurelioklv.kat.core.di.networkModule
import com.aurelioklv.kat.core.di.repositoryModule
import com.aurelioklv.kat.di.useCaseModule
import com.aurelioklv.kat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ThemeManager.applyTheme(this)
        startKoin {
            androidContext(this@KatApplication)
            modules(
                listOf(
                    useCaseModule,
                    viewModelModule,
                    repositoryModule,
                    databaseModule,
                    networkModule,
                    dataStoreModule
                )
            )
        }
    }
}