package com.demo.riskyrxkoinmosbimvidemo.executor

import com.demo.data_risky.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiThread @Inject constructor(): PostExecutionThread{
    override fun getSchedular(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}