package com.coe.kotaf.features

import com.coe.kotaf.base.BaseGUI
import com.coe.kotaf.pages.HomePage
import com.coe.kotaf.pages.steps
import org.junit.jupiter.api.Test

class SearchTest : BaseGUI() {
    @Test
    fun `Search results contain searching keyword`() {
        steps<HomePage> {
            openPage()
            search("Hoodie")
            TODO("Add assertions")
        }
    }
}