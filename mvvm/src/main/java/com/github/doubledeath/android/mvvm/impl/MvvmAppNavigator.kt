package com.github.doubledeath.android.mvvm.impl

import android.content.Intent
import android.os.Bundle
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.base.MvvmBaseActivity
import com.github.doubledeath.android.mvvm.base.MvvmBaseDelegate
import com.github.doubledeath.android.mvvm.base.MvvmBaseNavigator
import kotlin.reflect.KClass

internal class MvvmAppNavigator : MvvmBaseNavigator<MvvmBaseActivity<*, *>>() {

    override fun navigateView(context: MvvmBaseActivity<*, *>, klass: KClass<out MvvmView>, tag: String, noHistory: Boolean) {
        val extras = Bundle()

        MvvmBaseDelegate.applyTag(extras, tag)

        context.startActivity(Intent(context, klass.java).putExtras(extras))

        if (noHistory) {
            context.finish()
        }
    }

    override fun navigateViewBack(context: MvvmBaseActivity<*, *>) {
        context.finish()
    }

}
