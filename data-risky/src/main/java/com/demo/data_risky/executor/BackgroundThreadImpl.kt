package com.demo.data_risky.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackgroundThreadImpl @Inject constructor(private val jobsExecutor: JobsExecutor): BackgroundThread {
    override fun getSchedular(): Scheduler {
        return Schedulers.from(jobsExecutor)
    }
}