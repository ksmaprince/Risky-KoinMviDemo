package com.demo.riskyrxkoinmosbimvidemo.di

import com.demo.data_risky.database.RoomDbHelper
import com.demo.data_risky.executor.BackgroundThread
import com.demo.data_risky.executor.BackgroundThreadImpl
import com.demo.data_risky.executor.JobsExecutor
import com.demo.data_risky.mapper.CharacterMapper
import com.demo.data_risky.mapper.ErrorExceptionMapper
import com.demo.data_risky.remote.RiskyNetworkDatasource
import com.demo.data_risky.repository.RiskyRepository
import com.demo.data_risky.storage.InternalStorageManager
import com.demo.data_risky.util.DateUtils
import com.demo.riskyrxkoinmosbimvidemo.executor.UiThread
import org.koin.dsl.module

val appModule = module {
    single { UiThread() }
    single<BackgroundThread> { BackgroundThreadImpl(JobsExecutor()) }
    single { DateUtils() }
}

val interactorModule = module { }


val internalStorageModule = module {
    single { InternalStorageManager(get()) }
    single { RoomDbHelper(get()) }
}

val mapperModule = module {
    single { CharacterMapper() }
    single { ErrorExceptionMapper() }
}

val networkModule = module {
    single { RiskyNetworkDatasource(get()) }
}

val presenterModule = module {
    factory { }
}

val repositoryModule = module {
    single { RiskyRepository(get(), get(), get()) }
}