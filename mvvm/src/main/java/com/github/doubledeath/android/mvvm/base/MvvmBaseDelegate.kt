package com.github.doubledeath.android.mvvm.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import com.github.doubledeath.android.mvvm.MvvmFacade
import kotlin.reflect.KClass

internal abstract class MvvmBaseDelegate<out VM : MvvmBaseViewModel, out B : ViewDataBinding, out C>
constructor(private val klass: KClass<VM>,
            private val navigator: MvvmBaseNavigator<C>) {

    protected lateinit var tag: String
        private set
    private var viewModel: VM? = null

    internal abstract fun binding(): B
    internal abstract fun context(): C

    internal fun onCreate(extras: Bundle?, savedInstanceState: Bundle?) {
        val extrasTag = extras?.let { getTag(it) }
        val savedInstanceStateTag = savedInstanceState?.let { getTag(it) }

        tag = extrasTag ?: savedInstanceStateTag ?: MvvmFacade.tagGenerator.generateTag(klass)
    }

    internal open fun onViewActive() {
        navigator.pool.putContext(tag, context())

        viewModel().onViewActive()
    }

    internal fun onSaveInstanceState(outState: Bundle?) {
        outState?.let { applyTag(it, tag) }
    }

    internal open fun onViewInactive() {
        viewModel().onViewInactive()

        navigator.pool.cleanContext(tag)
    }

    internal open fun onDestroy() {
        if (!MvvmFacade.viewModelMapper.toSingle(klass)) {
            navigator.pool.cleanViewModel(tag)
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal fun viewModel(): VM {
        val viewModel = viewModel ?: navigator.pool.provideViewModel(tag, klass) as VM

        if (this.viewModel === null) {
            this.viewModel = viewModel
        }

        return viewModel
    }

    internal companion object {

        private val KEY_TAG: String = "com.github.doubledeath.android.mvvm.KEY_TAG"

        internal fun getTag(bundle: Bundle): String? {
            return bundle.getString(KEY_TAG)
        }

        internal fun applyTag(bundle: Bundle, tag: String) {
            return bundle.putString(KEY_TAG, tag)
        }

    }

}
