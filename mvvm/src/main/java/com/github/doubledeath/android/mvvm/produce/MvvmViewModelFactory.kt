package com.github.doubledeath.android.mvvm.produce

import android.os.Bundle
import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import kotlin.reflect.KClass

interface MvvmViewModelFactory {

    fun createViewModel(klass: KClass<out MvvmBaseViewModel>, args: Bundle?): MvvmBaseViewModel

}
