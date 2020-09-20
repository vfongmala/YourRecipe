package com.vfongmala.yourrecipe.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.entity.RecipePreview
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomePresenter(
    private val view: HomeView,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModelProvider: ViewModelProvider,
    private val searchInteractor: SearchInteractor
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
                    RecipePreview(
                        it.id,
                        it.title,
                        it.image
                    )
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
}