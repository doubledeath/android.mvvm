package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

open class MvvmFragmentViewModel : MvvmBaseViewModel() {

    @Suppress("UNUSED")
    protected fun navigateTo(klass: KClass<out MvvmFragmentViewModel>, noHistory: Boolean = false) {
        super.unsafeNavigateTo(klass, noHistory)
    }

}
