package com.vfongmala.yourrecipe.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val text: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}