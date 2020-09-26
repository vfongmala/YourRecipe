package com.vfongmala.yourrecipe.data_di

import com.vfongmala.yourrecipe.data.SearchApiImpl
import com.vfongmala.yourrecipe.data_contract.Api
import com.vfongmala.yourrecipe.data_contract.SearchApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideSearchApi(api: Api): SearchApi {
        return SearchApiImpl(api)
    }
}