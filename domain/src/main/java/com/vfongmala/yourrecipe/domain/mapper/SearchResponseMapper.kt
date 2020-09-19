package com.vfongmala.yourrecipe.domain.mapper

import com.vfongmala.yourrecipe.data_contract.entity.RecipeResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import com.vfongmala.yourrecipe.domain_contract.entity.SearchResult
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper

class SearchResponseMapper : Mapper<SearchResponse, SearchResult> {

    override fun map(input: SearchResponse): SearchResult {
        return SearchResult(
            result = mapRecipeResult(input.results),
            totalResults = input.totalResults ?: 0
        )
    }

    private fun mapRecipeResult(response: List<RecipeResponse>?): List<Recipe> {
        return response?.filter {
            it.id != 0
        }?.map {
            Recipe(
                it.id ?: 0,
                it.title ?: "",
                it.image ?: ""
            )
        } ?: listOf()
    }
}