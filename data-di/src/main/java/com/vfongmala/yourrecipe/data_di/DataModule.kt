package com.vfongmala.yourrecipe.data_di

import com.vfongmala.yourrecipe.data.SearchApiImpl
import com.vfongmala.yourrecipe.data_contract.SearchApi
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideSearchApi(): SearchApi {
        return SearchApiImpl()
    }
}