package com.coe.kotaf.features

import com.coe.kotaf.base.BaseGUI
import com.coe.kotaf.pages.HomePage
import com.coe.kotaf.pages.steps
import org.junit.jupiter.api.Test

class LoginTest : BaseGUI() {
    @Test
    fun `Login Success and Logout Success`() {
        steps<HomePage> {
            openPage()
            loginWithDefaultAccount()
            shouldDisplayMessage("You are now logged in")
            logout()
            shouldDisplayMessage("You are now logged out")
        }
    }
}
