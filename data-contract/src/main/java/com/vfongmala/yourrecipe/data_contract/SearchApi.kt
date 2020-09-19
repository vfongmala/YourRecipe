package com.vfongmala.yourrecipe.data_contract

import com.vfongmala.yourrecipe.data_contract.entity.RandomRecipesResponse
import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import io.reactivex.rxjava3.core.Observable

interface SearchApi {
    fun search(name: String): Observable<SearchResponse>
    fun recipeInfo(id: Int): Observable<RecipeInfoResponse>
    fun randomRecipes(amount: Int): Observable<RandomRecipesResponse>
}