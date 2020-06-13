package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class CartItem : AbstractComponent() {
    @FindBy(css = ".cart__list__item__details p")
    lateinit var itemPriceLabel: WebElement

    @FindBy(css = ".cart__list__item__details a p")
    lateinit var itemNameLabel: WebElement

    @FindBy(css = ".cart__list__item__details .cart__list__item__details__variant span:first-child")
    lateinit var itemSizeLabel: WebElement

    @FindBy(css = ".cart__list__item__details .cart__list__item__details__variant span~span")
    lateinit var itemQuantityLabel: WebElement

    val itemPrice by lazy { itemPriceLabel.text.trim() }
    val itemName by lazy { itemNameLabel.text.trim().toUpperCase() }
    val itemSize by lazy { itemSizeLabel.text.trim() }
    val itemQuantity by lazy {
        itemQuantityLabel.text.split(":")[1].trim().toInt() }
}