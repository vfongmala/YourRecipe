package com.vfongmala.yourrecipe.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomePresenter(
    private val view: HomeView,
//    private val lifecycleOwner: LifecycleOwner,
//    private val viewModelProvider: ViewModelProvider,
    private val searchInteractor: SearchInteractor,
    private val recipeInfoMapper: Mapper<RecipeInfo, RecipePreview>,
    private val schedulersFactory: SchedulersFactory
) {

//    private lateinit var homeViewModel: HomeViewModel

    fun init() {
//        homeViewModel = viewModelProvider.get(HomeViewModel::class.java)
//
//        homeViewModel.list.observe(lifecycleOwner, {
//            view.showResult(it)
//        })

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
//        homeViewModel.list.value = result
        view.updateData(result)
    }

    fun selectRecipe(recipe: RecipePreview) {
        view.openRecipe(recipe)
    }
}