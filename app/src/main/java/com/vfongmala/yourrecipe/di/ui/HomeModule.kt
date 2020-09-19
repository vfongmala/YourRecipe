package com.vfongmala.yourrecipe.di.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.ui.home.HomeFragment
import com.vfongmala.yourrecipe.ui.home.HomePresenter
import com.vfongmala.yourrecipe.ui.home.HomeView
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideHomeView(fragment: HomeFragment): HomeView = fragment

    @Provides
    fun provideLifeCycleOwner(fragment: HomeFragment): LifecycleOwner = fragment

    @Provides
    fun provideViewModelProvider(fragment: HomeFragment): ViewModelProvider = ViewModelProvider(fragment)

    @Provides
    fun provideHomePresenter(
        view: HomeView,
        lifeCycleOwner: LifecycleOwner,
        viewModelProvider: ViewModelProvider,
        searchInteractor: SearchInteractor
    ): HomePresenter {
        return HomePresenter(
            view,
            lifeCycleOwner,
            viewModelProvider,
            searchInteractor
        )
    }
}