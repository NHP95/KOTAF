package com.coe.kotaf.pages

import com.coe.kotaf.base.BasePage
import com.coe.kotaf.components.CartForm
import com.coe.kotaf.components.MainMenu
import com.coe.kotaf.components.SizeForm
import com.coe.kotaf.elements.widgets.Button
import com.coe.kotaf.elements.widgets.InputField
import com.coe.kotaf.enum.waitUntil
import com.coe.kotaf.model.Product
import io.kotlintest.matchers.collections.shouldContain
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions

class ProductDetailPage : BasePage() {
    @FindBy(className = "product-page__product__gallery")
    lateinit var productImage: WebElement

    @FindBy(css = ".product-description h3")
    lateinit var productNameLabel: WebElement

    @FindBy(css = ".product-description h4")
    lateinit var productPriceLabel: WebElement

    @FindBy(className = "product-description__variant-picker")
    lateinit var sizePicker: WebElement

    @FindBy(id = "modal-root")
    lateinit var sizeForm: SizeForm

    @FindBy(css = ".product-description__quantity-input input")
    lateinit var quantityInputField: InputField

    @FindBy(css = "button.product-description__action")
    lateinit var addToBasketButton: Button

    @FindBy(className = "cart")
    lateinit var cartForm: CartForm

    @FindBy(id = "header")
    lateinit var mainMenu: MainMenu

    fun addProductToCart(quantity: Int = 1): Product {
        waitForProductLoad()
        sizePicker.click()
        val sizeValue = sizeForm.selectRandomSize()
        quantityInputField.clearAndInput(quantity.toString())
        val product = Product(
            productNameLabel.text.trim(),
            sizeValue,
            productPriceLabel.text.split(" ").last(),
            quantity
        )
        addToBasketButton.click()
        return product
    }

    fun shouldDisplayInCart(product: Product) {
        /*
        There is a front-end error of properties missing thrown in console log
        when performing click action too fast
         */
        Thread.sleep(1500)
        mainMenu.openCartForm()
        cartForm.getItemsAddedToCart() shouldContain product
    }

    private fun waitForProductLoad() = waitUntil(ExpectedConditions.visibilityOf(productImage))
}