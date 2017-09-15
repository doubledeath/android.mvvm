package com.github.doubledeath.android.mvvm.base

import android.databinding.BaseObservable
import android.support.annotation.CallSuper
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.KClass

abstract class MvvmBaseViewModel internal constructor() : BaseObservable() {

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

    internal fun unsafeNavigateTo(klass: KClass<out MvvmBaseViewModel>) {
        navigator.navigate(MvvmBaseNavigator.Command(tag, klass))
    }

    @Suppress("UNUSED")
    protected fun navigateBack() {
        navigator.navigateBack(tag)
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
