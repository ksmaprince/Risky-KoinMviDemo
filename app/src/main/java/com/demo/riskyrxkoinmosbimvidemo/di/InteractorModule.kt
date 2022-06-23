package com.demo.riskyrxkoinmosbimvidemo.di

import com.demo.data_risky.executor.*
import com.demo.data_risky.interactor.CharacterInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    @Singleton
    fun providePostExecution() = PostExecutionThreadImpl()

    @Provides
    @Singleton
    fun provideBackgroundExecution(jobsExecutor: JobsExecutor)= BackgroundThreadImpl(jobsExecutor)
}