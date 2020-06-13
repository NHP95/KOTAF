package com.coe.kotaf.pages

import com.coe.kotaf.base.BasePage
import com.coe.kotaf.components.LoginForm
import com.coe.kotaf.components.MainMenu
import com.coe.kotaf.components.SearchForm
import com.coe.kotaf.enum.waitUntil
import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.shouldBe
import org.openqa.selenium.By
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions

class HomePage : BasePage() {
    @FindBy(id = "header")
    lateinit var mainMenu: MainMenu

    @FindBy(className = "search")
    lateinit var searchForm: SearchForm

    @FindBy(className = "login")
    lateinit var loginForm: LoginForm

    fun loginWithDefaultAccount() = login("", "")

    fun login(username: String, password: String) {
        mainMenu.openLoginForm()
        loginForm.inputCredentialsAndClickLogin(username, password)
    }

    fun logout() {
        mainMenu.openUserForm()
        waitUntil(ExpectedConditions.visibilityOf(mainMenu.logoutLink))
        mainMenu.logoutLink.click()
    }

    fun search(searchString: String) {
        mainMenu.openSearchForm()
        searchForm.inputSearchString(searchString)
    }

    fun shouldContainSearchString(searchString: String) =
        searchForm.searchResults.forEach {
            it.productTitle.toLowerCase() shouldContain searchString.toLowerCase()
        }

    fun shouldDisplayMessage(message: String) {
        waitUntil(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space(.)='$message']")
            )
        ).isDisplayed shouldBe true
    }
}
