package com.vfongmala.yourrecipe.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
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

        homeViewModel.text.observe(lifecycleOwner, {
            view.setText(it)
        })

        call()
    }

    private fun call() {
        searchInteractor.search("Steak")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.result
                homeViewModel.text.value = "We have ${it.totalResults}"
            }
    }
}