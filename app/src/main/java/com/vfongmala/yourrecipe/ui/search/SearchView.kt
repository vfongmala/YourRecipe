package com.vfongmala.yourrecipe.ui.search

import com.vfongmala.yourrecipe.ui.entity.RecipePreview

interface SearchView {
    fun updateModel(list: List<RecipePreview>)
    fun showLoading()
    fun showNoResult()
    fun showError(message: String)
    fun openRecipe(data: RecipePreview)
    fun hideKeyboard()
}