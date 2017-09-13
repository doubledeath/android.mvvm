package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.MvvmViewModel
import kotlin.reflect.KClass

interface MvvmViewModelFactory {
    fun createViewModel(klass: KClass<out MvvmViewModel>): MvvmViewModel
}