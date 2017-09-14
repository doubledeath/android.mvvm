package com.github.doubledeath.android.mvvm

import android.databinding.BaseObservable
import android.support.annotation.CallSuper
import com.github.doubledeath.android.mvvm.base.MvvmBaseNavigator
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.KClass

abstract class MvvmViewModel : BaseObservable() {

    internal lateinit var tag: String
    internal lateinit var navigator: MvvmBaseNavigator<*>
    private var isViewActive = AtomicBoolean(false)

    @CallSuper
    override fun notifyChange() {
        if (isViewActive.get()) {
            super.notifyChange()
        }
    }

    @CallSuper
    override fun notifyPropertyChanged(fieldId: Int) {
        if (isViewActive.get()) {
            super.notifyPropertyChanged(fieldId)
        }
    }

    @Suppress("UNUSED")
    protected fun navigateTo(klass: KClass<out MvvmViewModel>) {
        navigator.navigate(MvvmBaseNavigator.Command(tag, klass))
    }

    internal fun onViewActive() {
        if (!isViewActive.getAndSet(true)) {
            notifyChange()
        }
    }

    internal fun onViewInactive() {
        isViewActive.set(false)
    }

}
