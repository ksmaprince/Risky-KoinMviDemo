package com.demo.data.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThreadImpl(private val jobsExecutor: JobsExecutor): BackgroundThread {
    override fun getSchedular(): Scheduler {
        return Schedulers.from(jobsExecutor)
    }
}