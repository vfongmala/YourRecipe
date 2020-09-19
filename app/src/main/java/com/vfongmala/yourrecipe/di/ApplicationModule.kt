package com.vfongmala.yourrecipe.di

import com.vfongmala.yourrecipe.MainActivity
import com.vfongmala.yourrecipe.di.ui.HomeModule
import com.vfongmala.yourrecipe.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeFragment(): HomeFragment
}