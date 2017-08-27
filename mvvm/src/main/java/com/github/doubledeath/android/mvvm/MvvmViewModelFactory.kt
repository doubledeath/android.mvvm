package com.github.doubledeath.android.mvvm

interface MvvmViewModelFactory {

    fun createViewModel(viewModelTag: String): MvvmViewModel<*>

}