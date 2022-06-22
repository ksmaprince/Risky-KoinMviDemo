package com.demo.riskyrxkoinmosbimvidemo

import android.app.Application
import com.demo.riskyrxkoinmosbimvidemo.di.*
import org.koin.core.context.startKoin
import java.util.logging.Level

class RiskyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule,
                interactorModule,
                internalStorageModule,
                mapperModule,
                networkModule,
                presenterModule,
                repositoryModule))
        }
    }
}