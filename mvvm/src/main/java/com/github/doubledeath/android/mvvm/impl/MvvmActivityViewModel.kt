package com.github.doubledeath.android.mvvm.impl

import android.os.Bundle
import com.github.doubledeath.android.mvvm.base.MvvmBaseNavigator
import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

open class MvvmActivityViewModel : MvvmBaseViewModel() {

    internal var selfNavigator: MvvmActivityNavigator = MvvmActivityNavigator()

    @Suppress("UNUSED")
    protected fun navigateTo(klass: KClass<out MvvmActivityViewModel>, args: Bundle? = null, noHistory: Boolean = false) {
        super.unsafeNavigateTo(klass, args, noHistory)
    }

    @Suppress("UNUSED")
    protected fun display(klass: KClass<out MvvmFragmentViewModel>, args: Bundle? = null) {
        selfNavigator.navigate(MvvmBaseNavigator.Command(tag, klass, args, true))
    }

}