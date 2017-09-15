package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

interface MvvmViewModelSingleChecker {

    fun isViewModelSingle(klass: KClass<out MvvmBaseViewModel>): Boolean {
        return false
    }

}