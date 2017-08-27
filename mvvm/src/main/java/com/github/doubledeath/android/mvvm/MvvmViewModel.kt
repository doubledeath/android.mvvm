package com.github.doubledeath.android.mvvm

import android.databinding.BaseObservable
import android.support.annotation.CallSuper

abstract class MvvmViewModel<in V : MvvmView>(private var tag: String) : BaseObservable() {

    private var isViewAttached: Boolean = false

    override fun notifyChange() {
        if (isViewAttached) {
            super.notifyChange()
        }
    }

    override fun notifyPropertyChanged(fieldId: Int) {
        if (isViewAttached) {
            super.notifyPropertyChanged(fieldId)
        }
    }

    @CallSuper
    fun onCreate() {

    }

    @CallSuper
    fun onAttachView(view: V) {
        isViewAttached = true

        notifyChange()
    }

    @CallSuper
    fun onDetachView(view: V) {
        isViewAttached = false
    }

    @CallSuper
    fun onDestroy() {
        MvvmFacade.instance.removeViewModel(tag)
    }

}