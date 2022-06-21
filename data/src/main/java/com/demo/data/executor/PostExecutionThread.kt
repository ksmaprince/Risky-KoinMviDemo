package com.demo.data.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getSchedular(): Scheduler
}