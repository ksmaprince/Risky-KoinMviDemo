package com.demo.riskyrxkoinmosbimvidemo.di

import com.demo.data_risky.executor.BackgroundThread
import com.demo.data_risky.executor.BackgroundThreadImpl
import com.demo.data_risky.executor.PostExecutionThread
import com.demo.data_risky.executor.PostExecutionThreadImpl
import com.demo.riskyrxkoinmosbimvidemo.executor.UiThread
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ExecutionModule {

    @Binds
    @Singleton
    abstract fun bindBackgroundTheread(backgroundThreadImpl: BackgroundThreadImpl): BackgroundThread

    @Binds
    @Singleton
    abstract fun bindPostExecutionThread(postExecutionThreadImpl: PostExecutionThreadImpl): PostExecutionThread

}