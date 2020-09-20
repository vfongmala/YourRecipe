package com.vfongmala.yourrecipe.core

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulersFactoryImpl: SchedulersFactory {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}