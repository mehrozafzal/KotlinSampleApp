package com.example.mobiquitytask.utils.rx

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject

object RxBus {
    private val behaviorSubject = BehaviorSubject.create<Any>()
    fun subscribe(action: Consumer<Any>): Disposable {
        return behaviorSubject.subscribe(action)
    }

    //use this method to send data
    fun publish(message: Any) {
        behaviorSubject.onNext(message)
    }
}