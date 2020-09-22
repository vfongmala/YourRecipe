package com.vfongmala.yourrecipe.ui.recipe

import com.vfongmala.yourrecipe.ui.entity.ViewDataWrapper

interface RecipeView {
    fun setRecipeTitle(title: String)
    fun showRecipeImage(image: String)
    fun showRecipe(recipe: List<ViewDataWrapper>)
    fun showError()
}