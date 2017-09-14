package com.github.doubledeath.android.mvvm.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.github.doubledeath.android.mvvm.MvvmApp
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel
import com.github.doubledeath.android.mvvm.impl.MvvmActivityPartner

abstract class MvvmBaseActivity<VM : MvvmViewModel, B : ViewDataBinding> : AppCompatActivity(), MvvmView {

    abstract val providedLayoutId: Int @LayoutRes get
    abstract val providedViewModelId: Int @IdRes get
    protected lateinit var viewModel: VM
        private set
    protected lateinit var binding: B
        private set
    private var partner: MvvmActivityPartner<VM, B, MvvmBaseActivity<VM, B>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        partner().onCreate(intent?.extras, savedInstanceState)

        viewModel = partner().viewModel()
        binding = partner().binding()
    }

    override fun onResume() {
        super.onResume()

        partner().onViewActive()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        partner().onSaveInstanceState(outState)
    }

    override fun onPause() {
        partner().onViewInactive()

        super.onPause()
    }

    override fun onDestroy() {
        partner().onDestroy()

        super.onDestroy()
    }

    private fun partner(): MvvmActivityPartner<VM, B, MvvmBaseActivity<VM, B>> {
        val partner = this.partner ?: MvvmActivityPartner(this, MvvmApp.navigator)

        if (this.partner === null) {
            this.partner = partner
        }

        return partner
    }

}
