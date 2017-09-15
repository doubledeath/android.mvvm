package com.github.doubledeath.android.mvvm.base

import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel
import kotlin.reflect.KClass

abstract class MvvmBaseNavigator<C> {

    internal val pool = Pool()

    fun navigate(command: Command) {
        val context = pool.searchContext(command.tag)
        val klass = MvvmFacade.viewMapper.viewModelToView(command.klass)
        val tag = MvvmFacade.tagGenerator.generateTag(command.klass)

        pool.provideViewModel(tag, command.klass)

        if (context !== null) {
            navigateView(context, klass, tag)
        }
    }

    protected abstract fun navigateView(context: C, klass: KClass<out MvvmView>, tag: String)

    class Command internal constructor(internal val tag: String,
                                       internal val klass: KClass<out MvvmViewModel>)

    internal inner class Pool {

        private val contextMap = HashMap<String, C>()
        private val viewModelMap = HashMap<String, MvvmViewModel>()

        internal fun putContext(tag: String, context: C) {
            contextMap[tag] = context
        }

        internal fun provideViewModel(tag: String, klass: KClass<out MvvmViewModel>): MvvmViewModel {
            val viewModel: MvvmViewModel

            if (viewModelMap.containsKey(tag)) {
                viewModel = viewModelMap.getValue(tag)
            } else {
                viewModel = MvvmFacade.viewModelFactory.createViewModel(klass)
                viewModel.tag = tag
                viewModel.navigator = this@MvvmBaseNavigator

                viewModelMap[tag] = viewModel
            }

            return viewModel
        }

        internal fun searchContext(tag: String): C? {
            return contextMap[tag]
        }

        internal fun cleanContext(tag: String) {
            contextMap -= tag
        }

        internal fun cleanViewModel(tag: String) {
            viewModelMap -= tag
        }

    }

}
