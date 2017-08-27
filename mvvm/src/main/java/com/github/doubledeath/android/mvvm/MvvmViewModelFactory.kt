package com.github.doubledeath.android.mvvm

/**
 * Created by doubledeath on 8/27/17.
 */
interface MvvmViewModelFactory {

    fun createViewModel(viewModelTag: String): MvvmViewModel<*>

}