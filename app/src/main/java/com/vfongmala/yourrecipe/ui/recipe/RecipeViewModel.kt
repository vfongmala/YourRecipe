package com.vfongmala.yourrecipe.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vfongmala.yourrecipe.ui.entity.ViewDataWrapper

class RecipeViewModel : ViewModel() {

    val data: MutableLiveData<List<ViewDataWrapper>> by lazy {
        MutableLiveData<List<ViewDataWrapper>>()
    }

    val image: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}