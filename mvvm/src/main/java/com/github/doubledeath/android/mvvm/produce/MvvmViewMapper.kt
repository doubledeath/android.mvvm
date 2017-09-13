package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.MvvmModel
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel
import kotlin.reflect.KClass

interface MvvmViewMapper {
    fun modelToView(klass: KClass<out MvvmModel>): KClass<out MvvmView>
    fun viewToModel(klass: KClass<out MvvmView>): KClass<out MvvmModel>
    fun viewToViewModel(klass: KClass<out MvvmView>): KClass<out MvvmViewModel>
}