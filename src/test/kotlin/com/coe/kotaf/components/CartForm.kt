package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.enum.waitForVisible
import com.coe.kotaf.model.Product
import org.openqa.selenium.support.FindBy

class CartForm : AbstractComponent() {
    @FindBy(className = "cart__list__item")
    lateinit var items: List<CartItem>

    fun getItemsAddedToCart() =
        items.map {
            it.waitForVisible()
            Product(it.itemName, it.itemSize, it.itemPrice, it.itemQuantity)
        }.toMutableList()

}