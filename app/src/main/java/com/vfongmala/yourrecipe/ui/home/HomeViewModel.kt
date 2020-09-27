package com.vfongmala.yourrecipe.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor,
    private val recipeInfoMapper: Mapper<RecipeInfo, RecipePreview>,
    private val schedulersFactory: SchedulersFactory
) : ViewModel() {
    val list: MutableLiveData<List<RecipePreview>> by lazy {
        MutableLiveData<List<RecipePreview>>()
    }

    val showLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val showError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    init {
        getRandomRecipes()
    }

    private fun getRandomRecipes() {
        showLoading.value = true

        searchInteractor.randomRecipes()
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .map { list ->
                list.map {
                    recipeInfoMapper.map(it)
                }
            }
            .subscribe( {
                showLoading.value = false
                showError.value = false
                list.value = it
            }, {
                showLoading.value = false
                showError.value = true
            })
    }
}