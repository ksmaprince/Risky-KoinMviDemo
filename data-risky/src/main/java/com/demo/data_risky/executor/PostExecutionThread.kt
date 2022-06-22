package com.demo.data_risky.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getSchedular(): Scheduler
}