package com.vfongmala.yourrecipe.ui.home

import com.vfongmala.yourrecipe.entity.RecipePreview

interface HomeView {
    fun showResult(results: List<RecipePreview>)
    fun showNoResult(message: String)
    fun goToSearchActivity()
}