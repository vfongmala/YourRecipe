package com.vfongmala.yourrecipe.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomePresenter(
    private val view: HomeView,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModelProvider: ViewModelProvider,
    private val searchInteractor: SearchInteractor,
    private val recipeInfoMapper: Mapper<RecipeInfo, RecipePreview>
) {

    private lateinit var homeViewModel: HomeViewModel

    fun init() {
        homeViewModel = viewModelProvider.get(HomeViewModel::class.java)

        homeViewModel.list.observe(lifecycleOwner, {
            view.showResult(it)
        })

        getRandomRecipes()
    }

    fun search() {
        view.goToSearchActivity()
    }

    private fun getRandomRecipes() {
        searchInteractor.randomRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.map {
                    recipeInfoMapper.map(it)
                }
            }
            .subscribe( {
                updateModel(it)
            }, {
                view.showNoResult("Something was wrong, please try again")
            })
    }

    private fun updateModel(result: List<RecipePreview>) {
        homeViewModel.list.value = result
    }

    fun selectRecipe(recipe: RecipePreview) {
        view.openRecipe(recipe.id)
    }
}