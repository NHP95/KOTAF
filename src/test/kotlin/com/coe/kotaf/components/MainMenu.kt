package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.enum.ProductType
import com.coe.kotaf.enum.waitUntil
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions

class MainMenu : AbstractComponent() {
    @FindBy(xpath = "//*[@data-cy='main-menu__item']")
    lateinit var navigationItems: List<WebElement>

    @FindBy(xpath = "//*[@data-testid='login-btn']")
    lateinit var loginIcon: WebElement

    @FindBy(xpath = "//*[@data-testid='user-btn']")
    lateinit var userIcon: WebElement

    @FindBy(xpath = "//*[@data-testid='logout-link']")
    lateinit var logoutLink: WebElement

    @FindBy(className = "main-menu__search")
    lateinit var searchIcon: WebElement

    @FindBy(className = "main-menu__cart")
    lateinit var cartIcon: WebElement

    @FindBy(className = "main-menu__cart__quantity")
    lateinit var cartQuantityLabel: WebElement

    fun openLoginForm() {
        waitUntil(ExpectedConditions.visibilityOf(loginIcon))
        loginIcon.click()
    }

    fun openSearchForm() {
        waitUntil(ExpectedConditions.visibilityOf(searchIcon))
        searchIcon.click()
    }

    fun openCartForm() {
        waitUntil(ExpectedConditions.visibilityOf(cartIcon))
        waitUntil(ExpectedConditions.elementToBeClickable(cartIcon))
        cartIcon.click()
    }

    fun getCartQuantity(): String {
        waitUntil(ExpectedConditions.visibilityOf(cartQuantityLabel))
        return cartQuantityLabel.text.trim()
    }

    fun openUserForm() {
        waitUntil(ExpectedConditions.elementToBeClickable(userIcon))
        userIcon.click()
    }

    fun goToProductPage(productType: ProductType) {
        waitUntil(ExpectedConditions.visibilityOfAllElements(navigationItems))
        val productLink = navigationItems.find { it.text.trim() == productType.type }
        productLink?.click()
    }
}