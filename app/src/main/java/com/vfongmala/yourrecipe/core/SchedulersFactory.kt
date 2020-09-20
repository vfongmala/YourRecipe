package com.vfongmala.yourrecipe.core

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersFactory {
    fun io(): Scheduler
    fun main(): Scheduler
}