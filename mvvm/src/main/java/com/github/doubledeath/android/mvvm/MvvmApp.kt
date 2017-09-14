package com.github.doubledeath.android.mvvm

import com.github.doubledeath.android.mvvm.impl.MvvmAppNavigator
import com.github.doubledeath.android.mvvm.impl.MvvmTagGenerator
import com.github.doubledeath.android.mvvm.produce.MvvmViewMapper
import com.github.doubledeath.android.mvvm.produce.MvvmViewModelFactory

object MvvmApp {

    internal lateinit var navigator: MvvmAppNavigator

    @Suppress("UNUSED")
    fun initialize(viewMapper: MvvmViewMapper,
                   viewModelFactory: MvvmViewModelFactory) {
        MvvmFacade.tagGenerator = MvvmTagGenerator()
        MvvmFacade.viewMapper = viewMapper
        MvvmFacade.viewModelFactory = viewModelFactory

        navigator = MvvmAppNavigator()
    }

}