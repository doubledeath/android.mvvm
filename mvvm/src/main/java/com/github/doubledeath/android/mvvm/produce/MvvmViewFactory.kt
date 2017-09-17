package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.base.MvvmBaseFragment
import kotlin.reflect.KClass

interface MvvmViewFactory {

    fun createFragment(klass: KClass<out MvvmView>): MvvmBaseFragment<*, *>

}
