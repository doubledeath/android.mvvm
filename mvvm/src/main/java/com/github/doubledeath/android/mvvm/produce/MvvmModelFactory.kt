package com.github.doubledeath.android.mvvm.produce

import com.github.doubledeath.android.mvvm.MvvmModel
import kotlin.reflect.KClass

interface MvvmModelFactory {
    fun createViewModel(klass: KClass<out MvvmModel>): MvvmModel
}