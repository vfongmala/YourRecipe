package com.vfongmala.yourrecipe.di.ui

import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.ui.search.SearchActivity
import com.vfongmala.yourrecipe.ui.search.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun provideSearchPresenter(
        activity: SearchActivity,
        searchInteractor: SearchInteractor,
        schedulersFactory: SchedulersFactory
    ): SearchPresenter {
        return SearchPresenter(
            activity, activity, ViewModelProvider(activity), searchInteractor, schedulersFactory
        )
    }
}