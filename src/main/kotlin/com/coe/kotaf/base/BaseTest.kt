package com.coe.kotaf.base

import com.coe.kotaf.extensions.DriverInterceptor
import com.coe.kotaf.extensions.ScreenshotOnFailureHandler
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.RegisterExtension

sealed class BaseTest
open class BaseGUI : BaseTest() {
    @RegisterExtension
    @JvmField
    val driverInterceptor = DriverInterceptor()

    @RegisterExtension
    @JvmField
    val screenshotOnFailureHandler = ScreenshotOnFailureHandler()

    /*
    The hooks are used to trigger the DriverInterceptor extension
     */
    @BeforeEach
    fun initDriver() {}

    @AfterEach
    fun terminateDriver() {}
}

open class BaseAPI : BaseTest()
