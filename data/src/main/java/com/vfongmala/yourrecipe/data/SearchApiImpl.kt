package com.vfongmala.yourrecipe.data

import com.vfongmala.yourrecipe.data_contract.Api
import com.vfongmala.yourrecipe.data_contract.SearchApi
import com.vfongmala.yourrecipe.data_contract.entity.RandomRecipesResponse
import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import io.reactivex.rxjava3.core.Observable

class SearchApiImpl(private val api: Api) : SearchApi {

    override fun search(name: String): Observable<SearchResponse> {
        return api.search(name, API_KEY)
    }

    override fun recipeInfo(id: Int): Observable<RecipeInfoResponse> {
        return api.recipeInfo(id, API_KEY)
    }

    override fun randomRecipes(amount: Int): Observable<RandomRecipesResponse> {
        return api.randomRecipes(amount, API_KEY)
    }

    companion object {
        const val API_KEY = "b94c479400694cafb5ceecf80f039a12"
    }
}