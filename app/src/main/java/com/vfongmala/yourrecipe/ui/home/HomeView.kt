package com.vfongmala.yourrecipe.ui.home

import com.vfongmala.yourrecipe.ui.entity.RecipePreview

interface HomeView {
    fun showResult(results: List<RecipePreview>)
    fun showNoResult(message: String)
    fun showLoading()
    fun goToSearchActivity()
    fun openRecipe(data: RecipePreview)
}