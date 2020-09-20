package com.vfongmala.yourrecipe.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vfongmala.yourrecipe.entity.RecipePreview

class HomeViewModel : ViewModel() {

    val list: MutableLiveData<List<RecipePreview>> by lazy {
        MutableLiveData<List<RecipePreview>>()
    }
}