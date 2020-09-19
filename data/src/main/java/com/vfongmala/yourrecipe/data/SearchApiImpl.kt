package com.vfongmala.yourrecipe.data

import com.vfongmala.yourrecipe.data_contract.Api
import com.vfongmala.yourrecipe.data_contract.SearchApi
import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SearchApiImpl : SearchApi {

    private var retrofit = Retrofit.Builder().baseUrl("https://api.spoonacular.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val api: Api by lazy {
        retrofit.create(Api::class.java)
    }


    override fun search(name: String): Observable<SearchResponse> {
        return api.search(name, API_KEY)
    }

    override fun recipeInfo(id: Int): Observable<RecipeInfoResponse> {
        return api.recipeInfo(id, API_KEY)
    }

    companion object {
        const val API_KEY = "b94c479400694cafb5ceecf80f039a12"
    }
}