package com.vfongmala.yourrecipe.domain

import com.vfongmala.yourrecipe.data_contract.SearchApi
import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.entity.SearchResult
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import io.reactivex.rxjava3.core.Observable

class SearchInteractorImpl(
    private val api: SearchApi,
    private val searchResponseMapper: Mapper<SearchResponse, SearchResult>,
    private val recipeInfoResponseMapper: Mapper<RecipeInfoResponse, RecipeInfo>
) : SearchInteractor {

    override fun search(name: String): Observable<SearchResult> {
        return api.search(name).map {
            searchResponseMapper.map(it)
        }
    }

    override fun recipeInfo(id: Int): Observable<RecipeInfo> {
        return api.recipeInfo(id).map {
            recipeInfoResponseMapper.map(it)
        }
    }

    override fun randomRecipes(): Observable<List<RecipeInfo>> {
        return api.randomRecipes(RANDOM_AMOUNT).map { response ->
            response.recipes?.map {
                recipeInfoResponseMapper.map(it)
            }?: listOf()
        }
    }

    companion object {
        const val RANDOM_AMOUNT = 20
    }
}