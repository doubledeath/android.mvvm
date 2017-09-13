package com.github.doubledeath.android.mvvm.impl

import android.databinding.BaseObservable
import android.support.annotation.CallSuper
import com.github.doubledeath.android.mvvm.MvvmViewModel

@Suppress("UNUSED")
abstract class MvvmBaseViewModel : BaseObservable(), MvvmViewModel {

    protected var isViewActive = false
        private set

    @CallSuper
    override fun notifyChange() {
        if (isViewActive) {
            super.notifyChange()
        }
    }

    @CallSuper
    override fun notifyPropertyChanged(fieldId: Int) {
        if (isViewActive) {
            super.notifyPropertyChanged(fieldId)
        }
    }

    @CallSuper
    override fun onViewActive() {
        if (!isViewActive) {
            isViewActive = true

            notifyChange()
        }
    }

    @CallSuper
    override fun onViewInactive() {
        if (isViewActive) {
            isViewActive = false
        }
    }

}