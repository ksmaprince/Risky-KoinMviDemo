package com.demo.data_risky.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PostExecutionThreadImpl @Inject constructor() : PostExecutionThread {
    override fun getSchedular(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}