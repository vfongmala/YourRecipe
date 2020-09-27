package com.vfongmala.yourrecipe.di

import com.vfongmala.yourrecipe.MainActivity
import com.vfongmala.yourrecipe.ui.home.HomeFragment
import com.vfongmala.yourrecipe.ui.recipe.RecipeActivity
import com.vfongmala.yourrecipe.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {

    // Activities
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector
    abstract fun bindRecipeActivity(): RecipeActivity


    // Fragment
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}