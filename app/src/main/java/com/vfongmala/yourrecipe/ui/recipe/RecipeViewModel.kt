package com.vfongmala.yourrecipe.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vfongmala.yourrecipe.entity.RecipeInfo

class RecipeViewModel : ViewModel() {

    val data: MutableLiveData<RecipeInfo> by lazy {
        MutableLiveData<RecipeInfo>()
    }
}