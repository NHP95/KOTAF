package com.coe.kotaf.elements.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.elements.Component
import org.openqa.selenium.WebElement

interface InputField : Component {
    fun clearAndInput(vararg chars: CharSequence?)
}

class InputFieldImpl(override val wrappedElement: WebElement) : AbstractComponent(wrappedElement), InputField {
    @Suppress("Occasionally use of Spread(*) operator")
    override fun clearAndInput(vararg chars: CharSequence?) {
        this.clear()
        chars.forEach {
            this.sendKeys(it)
        }
    }
}
