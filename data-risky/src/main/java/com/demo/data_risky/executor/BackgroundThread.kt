package com.demo.data_risky.executor

import io.reactivex.Scheduler

interface BackgroundThread {
    fun getSchedular(): Scheduler
}