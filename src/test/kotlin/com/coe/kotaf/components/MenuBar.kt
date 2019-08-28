package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.elements.Component
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

interface MenuBar : Component {
    fun goToSearchPage()
}

class MenuBarImpl(override val wrappedElement: WebElement) : AbstractComponent(wrappedElement), MenuBar {

    @FindBy(linkText = "View Feedbacks")
    lateinit var viewFeedbacksOption: WebElement

    @FindBy(linkText = "Search All")
    lateinit var searchAllOption: WebElement

    override fun goToSearchPage() {
        viewFeedbacksOption.click()
        searchAllOption.click()
    }
}
