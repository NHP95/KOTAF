package com.coe.kotaf.elements.factory

import com.coe.kotaf.elements.Component
import com.coe.kotaf.elements.support.ExtendedFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

@Suppress("UNCHECKED_CAST")
fun <T : Component> createComponent(elementClass: Class<T>, wrappedElement: WebElement): T {
    val implCls: KClass<out T> = Class.forName(elementClass.name).kotlin as KClass<out T>
    return implCls.primaryConstructor?.call()?.apply {
        this.wrappedElement = wrappedElement
        PageFactory.initElements(ExtendedFieldDecorator(this.wrappedElement), this)
    } ?: throw IllegalArgumentException("No valid primary constructor found for ${elementClass.simpleName}.")
}
