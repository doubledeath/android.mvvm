package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.MvvmViewModel
import com.github.doubledeath.android.mvvm.produce.MvvmViewModelFactory
import kotlin.reflect.KClass

internal class MvvmViewModelHost
constructor(private val factory: MvvmViewModelFactory) : MvvmBaseHost<MvvmViewModel>() {

    override fun createTarget(klass: KClass<out MvvmViewModel>): MvvmViewModel {
        return factory.createViewModel(klass)
    }

}