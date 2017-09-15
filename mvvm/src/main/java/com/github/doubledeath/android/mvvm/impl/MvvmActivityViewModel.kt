package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

open class MvvmActivityViewModel : MvvmBaseViewModel() {

    @Suppress("UNUSED")
    protected fun navigateTo(klass: KClass<out MvvmActivityViewModel>) {
        super.unsafeNavigateTo(klass)
    }

}