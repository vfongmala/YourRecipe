package com.vfongmala.yourrecipe.domain_contract

import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.entity.SearchResult
import io.reactivex.rxjava3.core.Observable

interface SearchInteractor {
    fun search(name: String): Observable<SearchResult>
    fun recipeInfo(id: Int): Observable<RecipeInfo>
    fun randomRecipes(): Observable<List<RecipeInfo>>
}