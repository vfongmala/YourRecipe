package com.vfongmala.yourrecipe.di.ui

import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.home.HomeFragment
import com.vfongmala.yourrecipe.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    fun provideHomePresenter(
        fragment: HomeFragment,
        searchInteractor: SearchInteractor,
        recipeInfoMapper: Mapper<RecipeInfo, RecipePreview>,
        schedulersFactory: SchedulersFactory
    ): HomePresenter {
        return HomePresenter(
            fragment,
//            fragment,
//            ViewModelProvider(fragment),
            searchInteractor,
            recipeInfoMapper,
            schedulersFactory
        )
    }
}