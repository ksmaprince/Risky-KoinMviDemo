package com.demo.data_risky.network.interceptor

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class HttpErrorEvent {
    companion object {
        private val eventSubject = PublishSubject.create<Event>()

        fun subscribe(): Observable<Event>{
            return eventSubject
        }

        fun emit(evet: Event){
            eventSubject.onNext(evet)
        }
    }

    class Event constructor(val message: String, val type: Type) {
        fun `is`(type: Type) {
            return
        }
    }

    enum class Type {
        UNAUTHORIZED, SERVER_ERROR, MAINTENANCE
    }
}