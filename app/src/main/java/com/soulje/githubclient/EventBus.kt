package com.soulje.githubclient

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class EventBus {
    open class Event

    private val bus = BehaviorSubject.create<Event>()
    //private val bus = PublishSubject.create<Event>()

    fun post(event: Event){
        bus.onNext(event)
    }

    fun get(): Observable<Event> = bus
}