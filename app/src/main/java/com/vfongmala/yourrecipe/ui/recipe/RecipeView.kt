package com.vfongmala.yourrecipe.ui.recipe

import com.vfongmala.yourrecipe.ui.entity.ViewDataWrapper

interface RecipeView {
    fun updateViewModel(recipe: List<ViewDataWrapper>)
    fun showError()
}