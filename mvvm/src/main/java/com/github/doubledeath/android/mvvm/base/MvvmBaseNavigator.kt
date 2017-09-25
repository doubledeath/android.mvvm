package com.github.doubledeath.android.mvvm.base

import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.MvvmView
import kotlin.reflect.KClass

internal abstract class MvvmBaseNavigator<C> {

    internal val pool = Pool()

    internal fun navigate(command: Command) {
        val context = pool.searchContext(command.tag)
        val klass = MvvmFacade.viewModelMapper.toView(command.klass)
        val tag = MvvmFacade.tagGenerator.generateTag(command.klass)

        pool.provideViewModel(tag, command.klass)

        context?.let { navigateView(it, klass, tag, command.noHistory) }
    }

    internal fun navigateBack(tag: String) {
        pool.searchContext(tag)?.let { navigateViewBack(it) }
    }

    protected abstract fun navigateView(context: C, klass: KClass<out MvvmView>, tag: String, noHistory: Boolean)
    protected abstract fun navigateViewBack(context: C)

    internal class Command internal constructor(internal val tag: String,
                                                internal val klass: KClass<out MvvmBaseViewModel>,
                                                internal val noHistory: Boolean)

    internal inner class Pool {

        private val contextMap = HashMap<String, C>()
        private val viewModelMap = HashMap<String, MvvmBaseViewModel>()

        internal fun putContext(tag: String, context: C) {
            contextMap[tag] = context
        }

        internal fun provideViewModel(tag: String, klass: KClass<out MvvmBaseViewModel>): MvvmBaseViewModel {
            val viewModel: MvvmBaseViewModel

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
