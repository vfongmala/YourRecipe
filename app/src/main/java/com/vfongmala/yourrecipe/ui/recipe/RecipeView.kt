package com.vfongmala.yourrecipe.ui.recipe

import com.vfongmala.yourrecipe.dao.entity.SavedRecipe
import com.vfongmala.yourrecipe.ui.entity.ViewDataWrapper

interface RecipeView {
    fun updateViewModel(recipe: List<ViewDataWrapper>)
    fun saveRecipe(savedRecipe: SavedRecipe)
    fun showError()
}