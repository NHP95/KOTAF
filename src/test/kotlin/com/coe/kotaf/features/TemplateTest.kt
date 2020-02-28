package com.coe.kotaf.features

import com.coe.kotaf.base.BaseGUI
import com.coe.kotaf.extensions.DriverContextProvider
import com.coe.kotaf.pages.HomePage
import com.coe.kotaf.pages.LoginPage
import io.kotlintest.shouldBe
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith

class TemplateTest : BaseGUI() {
    private val loginPage: LoginPage by lazy { LoginPage() }
    private val homePage: HomePage by lazy { HomePage() }

    @TestTemplate
    @ExtendWith(DriverContextProvider::class)
    fun `Login Success and Logout Success`() {
        loginPage.openPage()
        loginPage.loginByCredentials("nierautomata", "kms")
        homePage.getCurrentPageTitle() shouldBe "Home | PA Tool"
        homePage.isAvatarDisplayed shouldBe true
        homePage.logout()
        homePage.isAvatarDisplayed shouldBe false
    }
}