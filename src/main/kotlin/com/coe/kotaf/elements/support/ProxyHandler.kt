package com.coe.kotaf.elements.support

import com.coe.kotaf.elements.Component
import com.coe.kotaf.elements.Element
import com.coe.kotaf.elements.factory.createComponent
import com.coe.kotaf.elements.factory.createElement
import org.openqa.selenium.WebElement
import org.openqa.selenium.WrapsElement
import org.openqa.selenium.interactions.Locatable
import org.openqa.selenium.support.pagefactory.ElementLocator
import java.lang.reflect.Proxy

@Suppress("UNCHECKED_CAST")
fun <T> proxyForComponent(
    elementType: Class<T>,
    loader: ClassLoader,
    locator: ElementLocator
): T {
    /*
    Proxy on wrappedElement only since the Component is not an interface
     */
    val proxyElement = proxyForLocator(WebElement::class.java, loader, locator)
    return createComponent(elementType as Class<out Component>, proxyElement) as T
}

@Suppress("UNCHECKED_CAST")
fun <T> proxyForLocator(
    elementType: Class<T>,
    loader: ClassLoader,
    locator: ElementLocator
): T =
    Proxy.newProxyInstance(
        loader,
        arrayOf(elementType, WrapsElement::class.java, Locatable::class.java),
        ElementInvocationHandler(locator, elementType)
    ) as T

@Suppress("UNCHECKED_CAST")
fun <T> proxyForListLocator(
    elementType: Class<T>,
    loader: ClassLoader,
    locator: ElementLocator
): List<T> =
    Proxy.newProxyInstance(
        loader,
        arrayOf(List::class.java),
        ElementListInvocationHandler(locator, elementType)
    ) as List<T>