package com.github.doubledeath.android.mvvm.impl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.base.MvvmBaseNavigator
import com.github.doubledeath.android.mvvm.base.MvvmBaseDelegate
import kotlin.reflect.KClass

class MvvmAppNavigator : MvvmBaseNavigator<Context>() {

    override fun navigateView(context: Context, klass: KClass<out MvvmView>, tag: String) {
        val extras = Bundle()

        MvvmBaseDelegate.applyTag(extras, tag)

        context.startActivity(Intent(context, klass.java).putExtras(extras))
    }

}
