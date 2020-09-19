package com.vfongmala.yourrecipe.data_contract.entity

data class SearchResponse(
    val results: List<RecipeResponse>?,
    val offset: Int?,
    val number: Int?,
    val totalResults: Int?
)
