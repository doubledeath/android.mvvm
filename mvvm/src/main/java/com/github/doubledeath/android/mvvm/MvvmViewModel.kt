package com.github.doubledeath.android.mvvm

import android.databinding.BaseObservable
import android.support.annotation.CallSuper

@Suppress("UNUSED")
abstract class MvvmViewModel<in V : MvvmView> : BaseObservable() {

    protected var isViewActive: Boolean = false
        private set

    override fun notifyChange() {
        if (isViewActive) {
            super.notifyChange()
        }
    }

    override fun notifyPropertyChanged(fieldId: Int) {
        if (isViewActive) {
            super.notifyPropertyChanged(fieldId)
        }
    }

    @CallSuper
    open fun onViewActive() {
        if (!isViewActive) {
            isViewActive = true

            notifyChange()
        }
    }

    @CallSuper
    open fun onViewInactive() {
        if (isViewActive) {
            isViewActive = false
        }
    }

}