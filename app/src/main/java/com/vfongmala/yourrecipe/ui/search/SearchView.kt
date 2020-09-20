package com.vfongmala.yourrecipe.ui.search

import com.vfongmala.yourrecipe.entity.RecipePreview

interface SearchView {
    fun showResult(list: List<RecipePreview>)
    fun showLoading()
    fun showNoResult()
    fun openRecipe(id: Int)
}