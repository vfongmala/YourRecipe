package com.vfongmala.yourrecipe.di.ui

import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipeDetail
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
        schedulersFactory: SchedulersFactory,
        mapper: Mapper<RecipeInfo, RecipeDetail>
    ): RecipePresenter {
        return RecipePresenter(
            activity, searchInteractor, schedulersFactory, mapper
        )
    }
}