package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.enum.waitUntil
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions

class SizeForm : AbstractComponent() {
    @FindBy(xpath = "//h4[normalize-space(.)='Please select Size']/../following-sibling::div/div")
    lateinit var sizeOptions: List<WebElement>

    fun selectRandomSize(): String {
        waitUntil(ExpectedConditions.visibilityOfAllElements(sizeOptions))
        return with(sizeOptions.random()) {
            val sizeValue = text
            click()
            return@with sizeValue
        }
    }
}