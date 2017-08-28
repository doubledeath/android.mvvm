package com.github.doubledeath.android.mvvm

import kotlin.reflect.KClass

interface MvvmViewModelFactory {

    fun <V : MvvmView> createViewModel(klass: KClass<V>): MvvmViewModel<*>

}