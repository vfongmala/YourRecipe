package com.vfongmala.yourrecipe.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor,
    private val schedulersFactory: SchedulersFactory,
    private val recipeMapper: Mapper<Recipe, RecipePreview>
): ViewModel() {
    val list: MutableLiveData<List<RecipePreview>> by lazy {
        MutableLiveData<List<RecipePreview>>()
    }

    val showLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val showError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val showNoResult: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun search(name: String) {
        showLoading.value = true
        searchInteractor.search(name.trim())
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .map { searchResult ->
                searchResult.result.map {
                    recipeMapper.map(it)
                }
            }.subscribe({
                showLoading.value = false
                showError.value = false
                if (it.isNotEmpty()) {
                    list.value = it
                    showNoResult.value = false
                } else {
                    showNoResult.value = true
                }
            }, {
                showLoading.value = false
                showError.value = true
                showNoResult.value = false
            })
    }
}