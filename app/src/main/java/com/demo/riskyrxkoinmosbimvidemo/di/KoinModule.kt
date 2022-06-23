package com.demo.riskyrxkoinmosbimvidemo.di

import com.demo.data_risky.database.RoomDbHelper
import com.demo.data_risky.executor.BackgroundThread
import com.demo.data_risky.executor.BackgroundThreadImpl
import com.demo.data_risky.executor.PostExecutionThread
import com.demo.data_risky.interactor.CharacterInteractor
import com.demo.data_risky.mapper.CharacterMapper
import com.demo.data_risky.mapper.ErrorExceptionMapper
import com.demo.data_risky.remote.RiskyNetworkDatasource
import com.demo.data_risky.repository.RiskyRepository
import com.demo.data_risky.storage.InternalStorageManager
import com.demo.data_risky.util.DateUtils
import com.demo.riskyrxkoinmosbimvidemo.MainPresenter
import com.demo.riskyrxkoinmosbimvidemo.executor.UiThread

/*val appModule = module {
    *//*single { UiThread() }
    single<BackgroundThread> { BackgroundThreadImpl(JobsExecutor()) }
    single { DateUtils() }

    single {
        CharacterInteractor(get(), get(), get())
    }
    single { InternalStorageManager(get()) }
    single { RoomDbHelper(get()) }

    single { CharacterMapper() }
    single { ErrorExceptionMapper() }

    factory { MainPresenter(get())}

    single { RiskyNetworkDatasource(get()) }

    single { RiskyRepository(get(), get(), get()) }*//*

    singleOf(::UiThread){bind<PostExecutionThread>()}
    singleOf(::BackgroundThreadImpl){bind<BackgroundThread>()}
    singleOf(::DateUtils)
    singleOf(::CharacterInteractor)
    singleOf(::InternalStorageManager)
    singleOf(::RoomDbHelper)
    singleOf(::CharacterMapper)
    singleOf(::ErrorExceptionMapper)
    factoryOf(::MainPresenter)
    singleOf(::RiskyNetworkDatasource)
    singleOf(::RiskyRepository)
}*/
