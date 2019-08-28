package com.coe.kotaf.elements.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.elements.Component
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

interface DropDown : Component {
    fun selectOptionByVisibleText(text: String)
}

class DropDownImpl(override val wrappedElement: WebElement) : AbstractComponent(wrappedElement), DropDown {
    private var selector: Select = Select(wrappedElement)
    override fun selectOptionByVisibleText(text: String) {
        selector.selectByVisibleText(text)
    }
}
