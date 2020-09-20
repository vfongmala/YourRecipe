package com.vfongmala.yourrecipe.di.ui

import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.ui.recipe.RecipeActivity
import com.vfongmala.yourrecipe.ui.recipe.RecipePresenter
import dagger.Module
import dagger.Provides

@Module
class RecipeModule {
    @Provides
    fun provideRecipePresenter(
        activity: RecipeActivity,
        searchInteractor: SearchInteractor,
        schedulersFactory: SchedulersFactory
    ): RecipePresenter {
        return RecipePresenter(
            activity, activity, ViewModelProvider(activity), searchInteractor, schedulersFactory
        )
    }
}