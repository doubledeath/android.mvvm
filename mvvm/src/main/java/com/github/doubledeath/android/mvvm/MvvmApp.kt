package com.github.doubledeath.android.mvvm

import com.github.doubledeath.android.mvvm.impl.MvvmAppNavigator
import com.github.doubledeath.android.mvvm.impl.MvvmBaseNavigator
import com.github.doubledeath.android.mvvm.impl.MvvmModelHost
import com.github.doubledeath.android.mvvm.impl.MvvmViewModelHost
import com.github.doubledeath.android.mvvm.produce.MvvmModelFactory
import com.github.doubledeath.android.mvvm.produce.MvvmViewMapper
import com.github.doubledeath.android.mvvm.produce.MvvmViewModelFactory
import java.util.concurrent.atomic.AtomicBoolean

object MvvmApp {

    lateinit var navigator: MvvmAppNavigator
        private set
    private lateinit var currentModel: MvvmModel

    private var isInitialized = AtomicBoolean(false)

    @Suppress("UNUSED")
    fun initialize(modelFactory: MvvmModelFactory,
                   viewMapper: MvvmViewMapper,
                   viewModelFactory: MvvmViewModelFactory,
                   initialNavigationCommand: MvvmBaseNavigator.Command) {
        if (isInitialized.getAndSet(true)) {
            throw IllegalStateException("Only one initialization of MvvmApp is allowed")
        }

        MvvmFacade.modelHost = MvvmModelHost(modelFactory)
        MvvmFacade.viewMapper = viewMapper
        MvvmFacade.viewModelHost = MvvmViewModelHost(viewModelFactory)

        navigator = MvvmAppNavigator(object : MvvmBaseNavigator.CurrentModelHost {
            override var currentModel
                get() = MvvmApp.currentModel
                set(value) {
                    MvvmApp.currentModel = value
                }
        }, initialNavigationCommand)
    }

}