package com.demo.riskyrxkoinmosbimvidemo.di

import com.demo.data_risky.executor.BackgroundThread
import com.demo.data_risky.executor.PostExecutionThread
import com.demo.data_risky.interactor.CharacterInteractor
import com.demo.data_risky.repository.RiskyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object PresenterModule {

    @Provides
    fun provideInteractor(
        repository: RiskyRepository,
        mainThread: PostExecutionThread,
        backgroundThread: BackgroundThread
    ): CharacterInteractor = CharacterInteractor(repository, mainThread, backgroundThread)
}