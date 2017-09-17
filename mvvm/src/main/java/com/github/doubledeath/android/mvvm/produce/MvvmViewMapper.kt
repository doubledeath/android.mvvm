package com.github.doubledeath.android.mvvm.produce

import android.support.annotation.IdRes
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

interface MvvmViewMapper {

    fun toViewModel(klass: KClass<out MvvmView>): KClass<out MvvmBaseViewModel>
    @IdRes
    fun toContainerViewId(klass: KClass<out MvvmView>): Int {
        return 0
    }

}
