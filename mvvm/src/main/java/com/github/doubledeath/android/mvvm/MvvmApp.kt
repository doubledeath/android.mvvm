package com.github.doubledeath.android.mvvm

import com.github.doubledeath.android.mvvm.impl.MvvmAppNavigator
import com.github.doubledeath.android.mvvm.impl.MvvmTagGenerator
import com.github.doubledeath.android.mvvm.produce.MvvmViewMapper
import com.github.doubledeath.android.mvvm.produce.MvvmViewModelFactory
import com.github.doubledeath.android.mvvm.produce.MvvmViewModelMapper

object MvvmApp {

    internal lateinit var navigator: MvvmAppNavigator

    @Suppress("UNUSED")
    fun initialize(viewMapper: MvvmViewMapper,
                   viewModelFactory: MvvmViewModelFactory,
                   viewModelMapper: MvvmViewModelMapper = object : MvvmViewModelMapper {}) {
        MvvmFacade.tagGenerator = MvvmTagGenerator()
        MvvmFacade.viewMapper = viewMapper
        MvvmFacade.viewModelFactory = viewModelFactory
        MvvmFacade.viewModelMapper = viewModelMapper

        navigator = MvvmAppNavigator()
    }

}
