package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.MvvmModel
import com.github.doubledeath.android.mvvm.produce.MvvmModelFactory
import kotlin.reflect.KClass

internal class MvvmModelHost
constructor(private val factory: MvvmModelFactory) : MvvmBaseHost<MvvmModel>() {

    override fun createTarget(klass: KClass<out MvvmModel>): MvvmModel {
        return factory.createViewModel(klass)
    }

}