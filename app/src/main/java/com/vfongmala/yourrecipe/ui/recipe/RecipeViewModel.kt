package com.vfongmala.yourrecipe.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Recipe"
    }

    val text: LiveData<String> = _text
}