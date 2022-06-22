package com.demo.riskyrxkoinmosbimvidemo.executor

import com.demo.data_risky.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UiThread : PostExecutionThread{
    override fun getSchedular(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}