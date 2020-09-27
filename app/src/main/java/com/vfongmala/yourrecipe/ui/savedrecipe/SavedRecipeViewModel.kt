package com.vfongmala.yourrecipe.ui.savedrecipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vfongmala.yourrecipe.dao.SavedRecipeDatabase
import com.vfongmala.yourrecipe.dao.SavedRecipeRepository
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe

class SavedRecipeViewModel(application: Application) : AndroidViewModel(application) {

    val data: LiveData<List<SavedRecipe>>

    private val repo: SavedRecipeRepository

    init {
        val dao = SavedRecipeDatabase.getDatabase(application).dao()
        repo = SavedRecipeRepository(dao)
        data = repo.savedRecipes
    }
}