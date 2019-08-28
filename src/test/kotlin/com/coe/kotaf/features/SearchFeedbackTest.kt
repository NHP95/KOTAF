package com.coe.kotaf.features

import com.automation.remarks.junit5.Video
import com.coe.kotaf.base.BaseGUI
import com.coe.kotaf.pages.HomePage
import com.coe.kotaf.pages.LoginPage
import com.coe.kotaf.pages.SearchPage
import com.coe.kotaf.utils.database
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class SearchFeedbackTest : BaseGUI() {
    private val searchPage by lazy { SearchPage() }
    private val homePage by lazy { HomePage() }

    init {
        database.connect()
    }

    @BeforeEach
    fun precondition() {
        LoginPage().apply {
            openPage()
            loginByCredentials("nierautomata", "kms")
        }
    }

    @Test
    @Tag("Smoke")
    fun `Verify my feedback list`() {
        homePage.menuBar.goToSearchPage()
        val feedbackInDB = searchPage.getFeedbacksForOthersFromDB(providerId = 2153)
        val feedbacks = with(searchPage) {
            viewOptionDropDown.selectOptionByVisibleText("My feedbacks for Others")
            enableAllStatus()
            doSearch()
            getAllReturnedFeedback()
        }
        feedbacks shouldHaveSize feedbackInDB.size
        feedbacks shouldBe feedbackInDB
    }

    @Test
    @Video(name = "Grammar")
    fun `Verify search success message`() {
        homePage.menuBar.goToSearchPage()
        searchPage.apply {
            viewOptionDropDown.selectOptionByVisibleText("My feedbacks for Others")
            enableAllStatus()
            doSearch()
            searchSuccessMessage shouldBe "Search Successfully."
        }
    }
}
