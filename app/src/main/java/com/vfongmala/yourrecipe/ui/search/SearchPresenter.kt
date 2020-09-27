package com.vfongmala.yourrecipe.ui.search

import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview

class SearchPresenter(
    private val view: SearchView,
    private val searchInteractor: SearchInteractor,
    private val schedulersFactory: SchedulersFactory,
    private val recipeMapper: Mapper<Recipe, RecipePreview>
) {
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
                view.showError("Something went wrong, please try again.")
            })
    }

    private fun updateModel(result: List<RecipePreview>) {
        if (result.isNotEmpty()) {
            view.updateModel(result)
        } else {
            view.showNoResult()
        }
    }

    fun selectRecipe(recipe: RecipePreview) {
        view.openRecipe(recipe)
    }
}