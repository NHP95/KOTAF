package com.coe.kotaf.elements.factory

import com.coe.kotaf.elements.Element
import org.openqa.selenium.WebElement
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

@Suppress("UNCHECKED_CAST")
fun <T : Element> createElement(elementClass: Class<T>, wrappedElement: WebElement): T {
    val implCls = Class.forName("${elementClass.name}Impl").kotlin as KClass<out T>
    return implCls.primaryConstructor?.call(wrappedElement)
        ?: throw IllegalArgumentException("No valid primary constructor found for ${implCls.simpleName}.")
}