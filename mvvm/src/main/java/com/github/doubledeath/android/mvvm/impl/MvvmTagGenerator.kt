package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import java.util.*
import kotlin.reflect.KClass

internal class MvvmTagGenerator {

    fun generateTag(klass: KClass<out MvvmBaseViewModel>): String {
        return if (MvvmFacade.viewModelMapper.toSingle(klass)) {
            klass.toString()
        } else {
            UUID.randomUUID().toString()
        }
    }

}
