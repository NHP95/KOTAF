package com.coe.kotaf.pages

import com.coe.kotaf.base.BasePage
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : BasePage> steps(actions: T.() -> Unit) = T::class.primaryConstructor?.call()
    ?.apply(actions)





