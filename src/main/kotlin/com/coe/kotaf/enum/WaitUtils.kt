package com.coe.kotaf.enum

import com.coe.kotaf.ELEMENT_TIMEOUT
import com.coe.kotaf.driver.driver
import com.coe.kotaf.elements.Element
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import java.lang.reflect.UndeclaredThrowableException
import java.time.Duration


fun <T : Element> T.waitForClickable(): T =
    this.apply { waitUntil(ExpectedConditions.elementToBeClickable(wrappedElement)) }

fun <T : Element> T.waitForVisible(): T =
    this.apply { waitUntil { wrappedElement.isDisplayed } }

fun <T> waitUntil(condition: ExpectedCondition<T>): T =
    getDefaultWait().until(condition)

fun <T> waitUntil(condition: (WebDriver) -> T): T =
    getDefaultWait().until(condition)

private fun getDefaultWait() = FluentWait<WebDriver>(driver)
    .withTimeout(Duration.ofSeconds(ELEMENT_TIMEOUT))
    .pollingEvery(Duration.ofMillis(500))
    .ignoreAll(
        mutableListOf(
            NoSuchElementException::class.java,
            UndeclaredThrowableException::class.java
        ) as Collection<Class<out Exception>>?
    )