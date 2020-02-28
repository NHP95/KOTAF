package com.coe.kotaf.extensions

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestTemplateInvocationContext
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider
import java.util.stream.Stream

class DriverContextProvider : TestTemplateInvocationContextProvider {
    /**
     * Provide [invocation contexts][TestTemplateInvocationContext]
     * for the test template method represented by the supplied `context`.
     *
     *
     * This method is only called by the framework if [.supportsTestTemplate]
     * previously returned `true` for the same [ExtensionContext].
     * Thus, this method must not return an empty `Stream`.
     *
     *
     * The returned `Stream` will be properly closed by calling
     * [Stream.close], making it safe to use a resource such as
     * [Files.lines()][java.nio.file.Files.lines].
     *
     * @param context the extension context for the test template method about
     * to be invoked; never `null`
     * @return a `Stream` of `TestTemplateInvocationContext`
     * instances for the invocation of the test template method; never `null`
     * or empty
     * @see .supportsTestTemplate
     *
     * @see ExtensionContext
     */
    override fun provideTestTemplateInvocationContexts(context: ExtensionContext?): Stream<TestTemplateInvocationContext> {
        val drivers = listOf("miss", "you", "so", "much", "if", "you", "come", "back")
        return drivers.map { driver ->  DriverInvocationContext(driver) as TestTemplateInvocationContext}.stream()
    }

    /**
     * Determine if this provider supports providing invocation contexts for the
     * test template method represented by the supplied `context`.
     *
     * @param context the extension context for the test template method about
     * to be invoked; never `null`
     * @return `true` if this provider can provide invocation contexts
     * @see .provideTestTemplateInvocationContexts
     *
     * @see ExtensionContext
     */
    override fun supportsTestTemplate(context: ExtensionContext?): Boolean = true

    private class DriverInvocationContext(val driverName: String) : TestTemplateInvocationContext {
        override fun getDisplayName(invocationIndex: Int): String {
            return driverName
        }
    }
}