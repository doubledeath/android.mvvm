package com.github.doubledeath.android.mvvm.impl

import kotlin.reflect.KClass

internal abstract class MvvmBaseHost<T : Any> {

    private val map: MutableMap<String, T> = HashMap()

    internal fun get(klass: KClass<out T>, tag: String): T {
        val target: T

        if (map.contains(tag)) {
            target = map.getValue(tag)
        } else {
            target = createTarget(klass)

            map[tag] = target
        }

        return target
    }

    internal fun remove(tag: String) {
        map -= tag
    }

    protected abstract fun createTarget(klass: KClass<out T>): T

}