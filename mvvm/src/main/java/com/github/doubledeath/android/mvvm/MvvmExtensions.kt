package com.github.doubledeath.android.mvvm

import com.github.doubledeath.android.mvvm.impl.MvvmActivity
import com.github.doubledeath.android.mvvm.impl.MvvmFragment
import kotlin.reflect.KClass

fun <V : MvvmView, VM : MvvmViewModel<V>, A : MvvmActivity<V, VM>> KClass<A>.getTagForFragment(): String {
    return this.toString()
}

fun <V : MvvmView, VM : MvvmViewModel<V>, F : MvvmFragment<V, VM>> KClass<F>.getTagForActivity(): String {
    return this.toString()
}