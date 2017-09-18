package com.github.doubledeath.android.mvvm.impl

import android.app.FragmentManager
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.base.MvvmBaseDelegate
import com.github.doubledeath.android.mvvm.base.MvvmBaseFragment
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal class MvvmFragmentDelegate<VM : MvvmFragmentViewModel, B : ViewDataBinding, F : MvvmBaseFragment<VM, B>>
/*constructor*/(private val fragment: F, navigator: MvvmActivityNavigator) : MvvmBaseDelegate<VM, B, FragmentManager>
/*super.constructor*/(MvvmFacade.viewMapper.toViewModel(fragment::class) as KClass<VM>, navigator) {

    private var binding: B? = null
    private var context: FragmentManager? = null

    override fun binding(): B {
        val binding = binding ?: DataBindingUtil.inflate(
                LayoutInflater.from(fragment.activity), fragment.providedLayoutId, null, false)

        if (this.binding === null) {
            binding.setVariable(fragment.providedViewModelId, viewModel())

            this.binding = binding
        }

        return binding
    }

    override fun context(): FragmentManager {
        val context = context ?: fragment.fragmentManager

        if (this.context === null) {
            this.context = context
        }

        return context
    }

    override fun onDestroy() {
        if (fragment.isRemoving || fragment.activity.isFinishing) {
            super.onDestroy()
        }
    }

}
