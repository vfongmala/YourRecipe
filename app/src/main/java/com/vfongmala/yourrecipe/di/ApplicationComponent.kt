package com.vfongmala.yourrecipe.di

import com.vfongmala.yourrecipe.MainApplication
import com.vfongmala.yourrecipe.data_di.DataModule
import com.vfongmala.yourrecipe.di.ui.PresentationModule
import com.vfongmala.yourrecipe.di.ui.ViewModelModule
import com.vfongmala.yourrecipe.domain_di.DomainModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    SchedulersModule::class,
    DataModule::class,
    DomainModule::class,
    PresentationModule::class,
    ViewModelModule::class
])
interface ApplicationComponent: AndroidInjector<MainApplication>