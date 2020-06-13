package com.coe.kotaf.pages

import com.coe.kotaf.base.BasePage
import com.coe.kotaf.enum.waitUntil
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions

class ProductPage : BasePage() {
    @FindBy(xpath = "//*[@data-cy='product-tile']")
    lateinit var productLinks: List<WebElement>

    fun selectARandomProduct() {
        this.waitForAllProductLoad()
        productLinks.random().click()
    }

    private fun waitForAllProductLoad() {
        waitUntil(ExpectedConditions.visibilityOfAllElements(productLinks))
    }
}