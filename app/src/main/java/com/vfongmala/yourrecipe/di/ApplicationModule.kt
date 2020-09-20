package com.vfongmala.yourrecipe.di

import com.vfongmala.yourrecipe.MainActivity
import com.vfongmala.yourrecipe.di.ui.HomeModule
import com.vfongmala.yourrecipe.di.ui.SearchModule
import com.vfongmala.yourrecipe.ui.home.HomeFragment
import com.vfongmala.yourrecipe.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindSearchActivity(): SearchActivity


    // Fragment
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeFragment(): HomeFragment
}