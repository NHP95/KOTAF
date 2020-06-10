package com.coe.kotaf.elements

import org.openqa.selenium.WebElement

interface Component : Element

abstract class AbstractComponent : Component {
    override lateinit var wrappedElement: WebElement
}