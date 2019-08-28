package com.coe.kotaf.features

import com.coe.kotaf.annotations.ExecuteWith
import com.coe.kotaf.base.BaseGUI
import com.coe.kotaf.pages.HomePage
import com.coe.kotaf.pages.LoginPage
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.openqa.selenium.remote.BrowserType

@ExecuteWith(BrowserType.CHROME)
class LoginTest : BaseGUI() {
    private val loginPage: LoginPage by lazy { LoginPage() }
    private val homePage: HomePage by lazy { HomePage() }

    @Test
    @Tag("Smoke")
    fun `Login Success and Logout Success`() {
        loginPage.openPage()
        loginPage.loginByCredentials("nierautomata", "kms")
        homePage.getCurrentPageTitle() shouldBe "Home | PA Tool"
        homePage.isAvatarDisplayed shouldBe true
        homePage.logout()
        homePage.isAvatarDisplayed shouldBe false
    }

    @ParameterizedTest
    @CsvSource(
        "thao,kms",
        "th,kms",
        "t,kms"
    )
    fun `Login with invalid credentials`(username: String, password: String) {
        loginPage.openPage()
        loginPage.loginByCredentials(username, password)
        loginPage.errorMessage.text shouldBe "Incorrect credentials, please try again."
    }

    @ParameterizedTest
    @CsvSource(
        "'','', Username may not be empty.",
        "'',kms, Username may not be empty.",
        "t,'', Password may not be empty."
    )
    fun `Login with blank credentials`(username: String, password: String, expectedMsg: String) {
        loginPage.openPage()
        loginPage.loginByCredentials(username, password)
        loginPage.popupErrorMessage.getAttribute("innerText") shouldBe expectedMsg
    }
}
