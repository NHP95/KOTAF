package com.coe.kotaf.elements.widgets

import com.coe.kotaf.elements.AbstractElement
import com.coe.kotaf.elements.Element
import org.openqa.selenium.WebElement

interface Button : Element {
    fun click()
}

class ButtonImpl(override var wrappedElement: WebElement) : AbstractElement(wrappedElement), Button {
    override fun click() =  wrappedElement.click()
}