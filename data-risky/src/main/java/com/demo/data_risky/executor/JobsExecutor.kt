package com.demo.data_risky.executor

import java.util.concurrent.*

class JobsExecutor : Executor {
    private val initialPoolSize = 3
    private val maxPoolSize = 5

    //Sets the amount of time an idle thread waits before waiting
    private val keepAliceTime = 10

    //Sets the Time Unit to seconds
    private val keepAliveTimeUnit = TimeUnit.SECONDS

    private val workQueue: BlockingQueue<Runnable>
    private val threadPoolExecutor: ThreadPoolExecutor
    private val threadFactory: ThreadFactory

    init {
        workQueue = LinkedBlockingDeque()
        threadFactory = JobThreadFactory()
        threadPoolExecutor = ThreadPoolExecutor(
            initialPoolSize, maxPoolSize, keepAliceTime.toLong(),
            keepAliveTimeUnit, workQueue, threadFactory
        )
    }

    private class JobThreadFactory : ThreadFactory {
        private val threadName = "android_"
        private var counter = 0
        override fun newThread(p0: Runnable?): Thread {
            return Thread(threadName + counter++)
        }
    }

    override fun execute(p0: Runnable?) {
        if (p0 == null) {
            throw IllegalArgumentException("Runnable to saveLanguage cannot be null")
        }
        threadPoolExecutor.execute(p0)
    }

}