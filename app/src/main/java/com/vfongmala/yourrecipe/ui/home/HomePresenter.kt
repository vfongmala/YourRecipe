package com.vfongmala.yourrecipe.ui.home

import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview

class HomePresenter(
    private val view: HomeView,
    private val searchInteractor: SearchInteractor,
    private val recipeInfoMapper: Mapper<RecipeInfo, RecipePreview>,
    private val schedulersFactory: SchedulersFactory
) {
    fun init() {
        getRandomRecipes()
    }

    fun search() {
        view.goToSearchActivity()
    }

    private fun getRandomRecipes() {
        view.showLoading()
        searchInteractor.randomRecipes()
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .map { list ->
                list.map {
                    recipeInfoMapper.map(it)
                }
            }
            .subscribe( {
                updateModel(it)
            }, {
                view.showNoResult("Something went wrong, please try again.")
            })
    }

    private fun updateModel(result: List<RecipePreview>) {
        view.updateData(result)
    }

    fun selectRecipe(recipe: RecipePreview) {
        view.openRecipe(recipe)
    }
}