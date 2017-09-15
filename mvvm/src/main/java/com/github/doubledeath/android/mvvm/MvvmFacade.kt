package com.github.doubledeath.android.mvvm

import com.github.doubledeath.android.mvvm.impl.MvvmTagGenerator
import com.github.doubledeath.android.mvvm.produce.MvvmViewMapper
import com.github.doubledeath.android.mvvm.produce.MvvmViewModelFactory
import com.github.doubledeath.android.mvvm.produce.MvvmViewModelSingleChecker

internal object MvvmFacade {

    lateinit var tagGenerator: MvvmTagGenerator
    lateinit var viewMapper: MvvmViewMapper
    lateinit var viewModelFactory: MvvmViewModelFactory
    lateinit var viewModelSingleChecker: MvvmViewModelSingleChecker

}
