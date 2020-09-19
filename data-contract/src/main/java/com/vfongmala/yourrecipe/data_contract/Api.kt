package com.vfongmala.yourrecipe.data_contract

import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("recipes/complexSearch")
    fun search(@Query("query") name: String, @Query("apiKey") key: String) : Observable<SearchResponse>

    @GET("recipes/{id}/information")
    fun recipeInfo(@Path("id") id: Int, @Query("apiKey") key: String) : Observable<RecipeInfoResponse>
}