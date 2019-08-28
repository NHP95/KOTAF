package com.coe.kotaf.annotations

import com.coe.kotaf.elements.Component
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class OverrideWith (
    val implementation: KClass<out Component> = Component::class
)