package com.coe.kotaf.elements

import org.openqa.selenium.WebElement

interface Element {
    var wrappedElement: WebElement
}

abstract class AbstractElement(override var wrappedElement: WebElement) : Element