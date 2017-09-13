package com.github.doubledeath.android.mvvm.impl

import android.content.Context
import android.content.Intent
import com.github.doubledeath.android.mvvm.MvvmView
import kotlin.reflect.KClass

class MvvmAppNavigator(currentModelHost: CurrentModelHost, initialNavigationCommand: Command) : MvvmBaseNavigator<Context>(currentModelHost, initialNavigationCommand) {

    override fun navigateView(context: Context, klass: KClass<out MvvmView>) {
        context.startActivity(Intent(context, klass.java))
    }

}