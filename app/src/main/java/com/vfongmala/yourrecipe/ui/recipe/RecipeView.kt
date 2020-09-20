package com.vfongmala.yourrecipe.ui.recipe

import com.vfongmala.yourrecipe.entity.RecipeInfo

interface RecipeView {
    fun showRecipe(recipe: RecipeInfo)
    fun showError()
}