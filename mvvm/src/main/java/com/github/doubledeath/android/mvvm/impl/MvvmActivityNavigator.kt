package com.github.doubledeath.android.mvvm.impl

import android.app.FragmentManager
import android.os.Bundle
import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.base.MvvmBaseDelegate
import com.github.doubledeath.android.mvvm.base.MvvmBaseNavigator
import kotlin.reflect.KClass

internal class MvvmActivityNavigator : MvvmBaseNavigator<FragmentManager>() {

    override fun navigateView(context: FragmentManager, klass: KClass<out MvvmView>, tag: String, noHistory: Boolean) {
        val transaction = context.beginTransaction()
        val arguments = Bundle()
        val fragment = MvvmFacade.viewFactory.createFragment(klass)

        MvvmBaseDelegate.applyTag(arguments, tag)

        fragment.arguments = arguments

        transaction.add(MvvmFacade.viewMapper.toContainerViewId(klass), fragment)

        if (!noHistory) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    override fun navigateViewBack(context: FragmentManager) {
        context.popBackStack()
    }

}
