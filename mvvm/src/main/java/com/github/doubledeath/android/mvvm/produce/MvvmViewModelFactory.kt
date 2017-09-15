package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

interface MvvmViewModelFactory {

    fun createViewModel(klass: KClass<out MvvmBaseViewModel>): MvvmBaseViewModel

}
