package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

interface MvvmViewModelMapper {

    fun toView(klass: KClass<out MvvmBaseViewModel>): KClass<out MvvmView>
    fun toSingle(klass: KClass<out MvvmBaseViewModel>): Boolean {
        return false
    }

}
