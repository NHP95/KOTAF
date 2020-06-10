package com.coe.kotaf.elements.widgets


import com.coe.kotaf.elements.AbstractElement
import com.coe.kotaf.elements.Element
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

interface DropDown : Element {
    fun selectOptionByVisibleText(text: String)
}

class DropDownImpl(override var wrappedElement: WebElement) : AbstractElement(wrappedElement), DropDown {
    private var selector: Select = Select(this.wrappedElement)
    override fun selectOptionByVisibleText(text: String) {
        selector.selectByVisibleText(text)
    }
}
