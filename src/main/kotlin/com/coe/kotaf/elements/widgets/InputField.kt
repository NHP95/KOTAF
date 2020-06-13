package com.coe.kotaf.elements.widgets

import com.coe.kotaf.elements.AbstractElement
import com.coe.kotaf.elements.Element
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

interface InputField : Element {
    fun clear()
    fun sendKeys(vararg chars: CharSequence?)
    fun clearAndInput(vararg chars: CharSequence?)
}

class InputFieldImpl(override var wrappedElement: WebElement) : AbstractElement(wrappedElement), InputField {
    override fun clear() = this.wrappedElement.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.SPACE)

    override fun sendKeys(vararg chars: CharSequence?) = chars.forEach {
        this.wrappedElement.sendKeys(it)
    }

    @Suppress("Occasionally use of Spread(*) operator")
    override fun clearAndInput(vararg chars: CharSequence?) {
        this.clear()
        this.sendKeys()
    }
}

