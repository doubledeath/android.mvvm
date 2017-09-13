package com.github.doubledeath.android.mvvm

import com.github.doubledeath.android.mvvm.impl.MvvmModelHost
import com.github.doubledeath.android.mvvm.impl.MvvmViewModelHost
import com.github.doubledeath.android.mvvm.produce.MvvmViewMapper

internal object MvvmFacade {

    internal lateinit var modelHost: MvvmModelHost
    internal lateinit var viewMapper: MvvmViewMapper
    internal lateinit var viewModelHost: MvvmViewModelHost

}