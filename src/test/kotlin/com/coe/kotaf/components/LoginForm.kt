package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.elements.widgets.Button
import com.coe.kotaf.elements.widgets.InputField
import com.coe.kotaf.enum.waitForClickable
import org.openqa.selenium.support.FindBy

class LoginForm : AbstractComponent() {
    @FindBy(name = "email")
    lateinit var emailInputField: InputField

    @FindBy(name = "password")
    lateinit var passwordInputField: InputField

    @FindBy(xpath = "//button[@type='submit']")
    lateinit var signInButton: Button

    fun inputCredentialsAndClickLogin(email: String, password: String) {
        emailInputField.waitForClickable()
        emailInputField.sendKeys(email)
        emailInputField.waitForClickable()
        passwordInputField.sendKeys(password)
        signInButton.click()
    }
}