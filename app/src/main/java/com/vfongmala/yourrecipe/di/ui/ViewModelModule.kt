package com.vfongmala.yourrecipe.di.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.ui.home.HomeViewModel
import com.vfongmala.yourrecipe.ui.recipe.RecipeViewModel
import com.vfongmala.yourrecipe.ui.search.SearchViewModel
import com.vfongmala.yourrecipe.ui.utils.ViewModelFactory
import com.vfongmala.yourrecipe.ui.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    abstract fun bindRecipeViewModel(viewModel: RecipeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}