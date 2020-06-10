package com.coe.kotaf.pages

import com.coe.kotaf.base.BasePage
import com.coe.kotaf.components.LoginForm
import com.coe.kotaf.components.SearchForm
import com.coe.kotaf.utils.waitUntil
import io.kotlintest.shouldBe
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions

class HomePage : BasePage() {
    @FindBy(xpath = "//*[@data-testid='login-btn']")
    lateinit var loginIcon: WebElement

    @FindBy(xpath = "//*[@data-testid='user-btn']")
    lateinit var userIcon: WebElement

    @FindBy(className = "main-menu__search")
    lateinit var searchIcon: WebElement

    @FindBy(className = "search")
    lateinit var searchForm: SearchForm

    @FindBy(className = "login")
    lateinit var loginForm: LoginForm

    @FindBy(xpath = "//*[@data-testid='logout-link']")
    lateinit var logoutLink: WebElement

    fun loginWithDefaultAccount() = login("", "")

    fun login(username: String, password: String) {
        waitUntil(ExpectedConditions.visibilityOf(loginIcon))
        loginIcon.click()
        loginForm.inputCredentialsAndClickLogin(username, password)
    }

    fun logout() {
        waitUntil(ExpectedConditions.elementToBeClickable(userIcon))
        userIcon.click()
        waitUntil(ExpectedConditions.visibilityOf(logoutLink))
        logoutLink.click()
    }

    fun search(searchString: String) {
        waitUntil(ExpectedConditions.visibilityOf(searchIcon))
        searchIcon.click()
        searchForm.inputSearchString(searchString)
    }

    fun shouldDisplayMessage(message: String) {
        waitUntil(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space(.)='$message']")
            )
        ).isDisplayed shouldBe true
    }
}
