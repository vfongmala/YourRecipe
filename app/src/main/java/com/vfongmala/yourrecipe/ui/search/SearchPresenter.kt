package com.vfongmala.yourrecipe.ui.search

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview

class SearchPresenter(
    private val view: SearchView,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModelProvider: ViewModelProvider,
    private val searchInteractor: SearchInteractor,
    private val schedulersFactory: SchedulersFactory,
    private val recipeMapper: Mapper<Recipe, RecipePreview>
) {

    private lateinit var viewModel: SearchViewModel

    fun init() {
        viewModel = viewModelProvider.get(SearchViewModel::class.java)

        viewModel.list.observe(lifecycleOwner, {
            view.showResult(it)
        })
    }

    fun search(name: String) {
        view.showLoading()
        view.hideKeyboard()
        searchInteractor.search(name.trim())
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .map { searchResult ->
                searchResult.result.map {
                    recipeMapper.map(it)
                }
            }.subscribe({
                updateModel(it)
            }, {
                view.showNoResult()
            })
    }

    private fun updateModel(result: List<RecipePreview>) {
        viewModel.list.value = result
    }

    fun selectRecipe(recipe: RecipePreview) {
        view.openRecipe(recipe)
    }
}