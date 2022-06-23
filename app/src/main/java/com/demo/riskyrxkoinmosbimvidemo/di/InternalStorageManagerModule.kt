package com.demo.riskyrxkoinmosbimvidemo.di

import android.content.Context
import com.demo.data_risky.storage.InternalStorageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object InternalStorageManagerModule {

    @Provides
    @Singleton
    fun provideInternalStorageManager(@ApplicationContext context: Context): InternalStorageManager{
        return InternalStorageManager(context)
    }
}