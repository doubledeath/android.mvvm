package com.github.doubledeath.android.mvvm.impl

import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.MvvmModel
import com.github.doubledeath.android.mvvm.MvvmView
import kotlin.reflect.KClass

abstract class MvvmBaseNavigator<in C>
constructor(private val currentModelHost: CurrentModelHost, initialNavigationCommand: Command) {

    internal val pool: Pool<C> = Pool()

    init {
        pool.put(initialNavigationCommand.klass)

        currentModelHost.currentModel = MvvmFacade.modelHost.get(initialNavigationCommand.klass, initialNavigationCommand.klass.toString())
    }

    fun getCurrentModel(): MvvmModel {
        return currentModelHost.currentModel
    }

    fun navigate(command: Command) {
        val currentModel = getCurrentModel()
        val context = pool.get(currentModel::class)

        pool.put(command.klass)

        if (!currentModel.isSingle) {
            MvvmFacade.modelHost.remove(currentModel::class.toString())
        }

        currentModelHost.currentModel = MvvmFacade.modelHost.get(command.klass, command.klass.toString())

        if (context !== null) {
            navigateView(context, MvvmFacade.viewMapper.modelToView(command.klass))
        }
    }

    protected abstract fun navigateView(context: C, klass: KClass<out MvvmView>)

    interface CurrentModelHost {
        var currentModel: MvvmModel
    }

    class Command constructor(val klass: KClass<out MvvmModel>)

    inner class Pool<C> {

        private val map: MutableMap<KClass<out MvvmModel>, C?> = HashMap()

        internal fun put(klass: KClass<out MvvmModel>) {
            map[klass] = null
        }

        internal fun put(klass: KClass<out MvvmView>, context: C) {
            val modelType = MvvmFacade.viewMapper.viewToModel(klass)

            if (!map.containsKey(modelType)) {
                throw IllegalStateException("Pool does not contains a model type for view type: " + klass)
            }

            map[modelType] = context
        }

        internal fun get(klass: KClass<out MvvmModel>): C? {
            return map[klass]
        }

        internal fun remove(klass: KClass<out MvvmView>) {
            map[MvvmFacade.viewMapper.viewToModel(klass)] = null
        }

    }

}