package com.vfongmala.yourrecipe.dao

import androidx.lifecycle.LiveData
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe

class SavedRecipeRepository(
    private val savedRecipeDao: RecipeDao
) {
    val savedRecipes: LiveData<List<SavedRecipe>> = savedRecipeDao.loadAll()

    fun save(savedRecipe: SavedRecipe) {
        savedRecipeDao.save(savedRecipe)
    }
}