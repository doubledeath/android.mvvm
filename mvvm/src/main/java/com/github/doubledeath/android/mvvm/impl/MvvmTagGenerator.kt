package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.MvvmViewModel
import java.util.*
import kotlin.reflect.KClass

internal class MvvmTagGenerator {

    fun generateTag(klass: KClass<out MvvmViewModel>): String {
        return UUID.randomUUID().toString()
    }

}
