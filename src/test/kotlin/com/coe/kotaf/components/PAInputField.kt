package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.elements.components.InputField
import org.openqa.selenium.WebElement
import org.slf4j.LoggerFactory

class PAInputField(override val wrappedElement: WebElement) : AbstractComponent(wrappedElement), InputField {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun clearAndInput(vararg chars: CharSequence?) {
        logger.info("Clear current data.")
        this.clear()
        logger.info("Recursively input new data.")
        chars.forEach {
            this.sendKeys(it)
        }
    }

}