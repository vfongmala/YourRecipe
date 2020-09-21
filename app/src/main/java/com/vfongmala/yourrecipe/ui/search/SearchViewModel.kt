package com.vfongmala.yourrecipe.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vfongmala.yourrecipe.ui.entity.RecipePreview

class SearchViewModel: ViewModel() {
    val list: MutableLiveData<List<RecipePreview>> by lazy {
        MutableLiveData<List<RecipePreview>>()
    }
}