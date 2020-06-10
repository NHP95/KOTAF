package com.coe.kotaf.components

import com.coe.kotaf.elements.AbstractComponent
import com.coe.kotaf.elements.widgets.Button
import com.coe.kotaf.elements.widgets.InputField
import com.coe.kotaf.utils.waitForVisible
import org.openqa.selenium.support.FindBy


class SearchForm : AbstractComponent() {
    @FindBy(css = ".search__input input")
    lateinit var searchInputField: InputField

    @FindBy(tagName = "button")
    lateinit var showAllResultsButton: Button

    fun inputSearchString(searchString: String) {
        searchInputField.waitForVisible()
        searchInputField.sendKeys(searchString)
        showAllResultsButton.waitForVisible()
    }
}