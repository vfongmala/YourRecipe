package com.vfongmala.yourrecipe.di

import com.vfongmala.yourrecipe.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {
    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity
}