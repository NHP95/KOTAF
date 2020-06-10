package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SearchResult : AbstractComponent() {
    @FindBy(tagName = "h4")
    lateinit var productTitleLabel: WebElement

    @FindBy(tagName = "p")
    lateinit var productCategoryLabel: WebElement

    val productTitle by lazy { productTitleLabel.text.trim() }
    val productCategory by lazy { productCategoryLabel.text.trim() }
}