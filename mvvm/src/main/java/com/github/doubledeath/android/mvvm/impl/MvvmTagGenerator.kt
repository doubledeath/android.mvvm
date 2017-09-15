package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.base.MvvmBaseViewModel
import java.util.*
import kotlin.reflect.KClass

internal class MvvmTagGenerator {

    fun generateTag(klass: KClass<out MvvmBaseViewModel>): String {
        return UUID.randomUUID().toString()
    }

}
