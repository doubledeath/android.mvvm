package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.base.MvvmBaseNavigator
import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

open class MvvmActivityViewModel : MvvmBaseViewModel() {

    internal var selfNavigator: MvvmActivityNavigator = MvvmActivityNavigator()

    @Suppress("UNUSED")
    protected fun navigateTo(klass: KClass<out MvvmActivityViewModel>, noHistory: Boolean = false) {
        super.unsafeNavigateTo(klass, noHistory)
    }

    @Suppress("UNUSED")
    protected fun display(klass: KClass<out MvvmFragmentViewModel>) {
        selfNavigator.navigate(MvvmBaseNavigator.Command(tag, klass, true))
    }

}