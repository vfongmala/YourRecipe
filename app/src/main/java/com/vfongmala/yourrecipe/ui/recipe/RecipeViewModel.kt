package com.vfongmala.yourrecipe.ui.recipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vfongmala.yourrecipe.dao.SavedRecipeDatabase
import com.vfongmala.yourrecipe.dao.SavedRecipeRepository
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe
import com.vfongmala.yourrecipe.ui.entity.ViewDataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    val data: MutableLiveData<List<ViewDataWrapper>> by lazy {
        MutableLiveData<List<ViewDataWrapper>>()
    }

    val image: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val repo: SavedRecipeRepository

    init {
        val dao = SavedRecipeDatabase.getDatabase(application).dao()
        repo = SavedRecipeRepository(dao)
    }

    fun save(savedRecipe: SavedRecipe) = viewModelScope.launch(Dispatchers.IO) {
        repo.save(savedRecipe)
    }
}