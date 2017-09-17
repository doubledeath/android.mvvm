package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

interface MvvmViewMapper {

    fun viewToViewModel(klass: KClass<out MvvmView>): KClass<out MvvmBaseViewModel>

}