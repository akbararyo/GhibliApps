package com.akbararyo.ghibliapps

import android.app.Application
import com.akbararyo.ghibliapps.core.di.databaseModule
import com.akbararyo.ghibliapps.core.di.networkModule
import com.akbararyo.ghibliapps.core.di.repositoryModule
import com.akbararyo.ghibliapps.di.useCaseModule
import com.akbararyo.ghibliapps.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}