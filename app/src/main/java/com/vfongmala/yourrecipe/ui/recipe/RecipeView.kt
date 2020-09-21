package com.vfongmala.yourrecipe.ui.recipe

import com.vfongmala.yourrecipe.ui.entity.ViewDataWrapper

interface RecipeView {
    fun showRecipe(recipe: List<ViewDataWrapper>)
    fun showError()
}