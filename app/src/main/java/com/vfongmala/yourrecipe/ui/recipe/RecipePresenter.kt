package com.vfongmala.yourrecipe.ui.recipe

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.entity.RecipeInfo

class RecipePresenter(
    private val view: RecipeView,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModelProvider: ViewModelProvider,
    private val searchInteractor: SearchInteractor,
    private val schedulersFactory: SchedulersFactory
) {

    private lateinit var viewModel: RecipeViewModel

    fun init() {
        viewModel = viewModelProvider.get(RecipeViewModel::class.java)

        viewModel.data.observe(lifecycleOwner, {
            view.showRecipe(it)
        })
    }

    fun loadRecipe(id: Int) {
        searchInteractor.recipeInfo(id)
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .map {
                RecipeInfo(
                    it.title,
                    it.image
                )
            }.subscribe({
                updateModel(it)
            }, {
                view.showError()
            })
    }

    private fun updateModel(result: RecipeInfo) {
        viewModel.data.value = result
    }
}