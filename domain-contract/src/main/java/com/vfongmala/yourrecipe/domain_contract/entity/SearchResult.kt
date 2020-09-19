package com.vfongmala.yourrecipe.domain_contract.entity

data class SearchResult(
    val result: List<Recipe>,
    val totalResults: Int
)