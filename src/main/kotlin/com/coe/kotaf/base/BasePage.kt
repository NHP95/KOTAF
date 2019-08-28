package com.coe.kotaf.base

import com.coe.kotaf.configuration
import com.coe.kotaf.driver.driver
import com.coe.kotaf.elements.support.ComponentFieldDecorator
import org.openqa.selenium.support.PageFactory

@Suppress("LeakingThis")
open class BasePage {
    private val baseURL: String = configuration.string("applicationUrl") ?: ""
    open val path: String = ""

    init {
        PageFactory.initElements(ComponentFieldDecorator(driver), this)
    }

    fun openPage() {
        driver.get(baseURL + path)
    }

    fun getCurrentPageTitle(): String = driver.title
    fun getCurrentURL(): String = driver.currentUrl
}
