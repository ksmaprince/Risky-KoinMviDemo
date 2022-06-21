package com.demo.data.executor

import io.reactivex.Scheduler

interface BackgroundThread {
    fun getSchedular(): Scheduler
}