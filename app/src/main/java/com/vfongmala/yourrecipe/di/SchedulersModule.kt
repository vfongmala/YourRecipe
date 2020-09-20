package com.vfongmala.yourrecipe.di

import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.core.SchedulersFactoryImpl
import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {
    @Provides
    fun provideSchedulerFactory(): SchedulersFactory = SchedulersFactoryImpl()
}