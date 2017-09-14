package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel
import kotlin.reflect.KClass

interface MvvmViewMapper {

    fun viewToViewModel(klass: KClass<out MvvmView>): KClass<out MvvmViewModel>
    fun viewModelToView(klass: KClass<out MvvmViewModel>): KClass<out MvvmView>

}