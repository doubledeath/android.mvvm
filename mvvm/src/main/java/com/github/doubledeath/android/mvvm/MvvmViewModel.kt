package com.github.doubledeath.android.mvvm

import android.databinding.BaseObservable

/**
 * Created by doubledeath on 8/27/17.
 */
open class MvvmViewModel<in V : MvvmView>(private var tag: String) : BaseObservable() {

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

    fun onCreate() {

    }

    fun onAttachView(view: V) {
        isViewAttached = true

        notifyChange()
    }

    fun onDetachView(view: V) {
        isViewAttached = false
    }

    fun onDestroy() {
        MvvmFacade.instance.removeViewModel(tag)
    }

}