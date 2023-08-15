package org.example.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.compileAndLintWithContext
import io.kotest.matchers.collections.shouldHaveSize
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
internal class MyRuleTest(private val env: KotlinCoreEnvironment) {

    @Test
    fun `reports enum without @Keep annotation`() {
        val code = """
            import androidx.annotation.Keep

            enum class MyEnum {
                VALUE1,
                VALUE2
            }
        """.trimIndent()

        val findings = EnumKeepAnnotation(Config.empty).compileAndLintWithContext(env, code)

        findings shouldHaveSize 1
    }

    @Test
    fun `doesn't report enum with @Keep annotation`() {
        val code = """
            import androidx.annotation.Keep

            @Keep
            enum class MyEnum {
                VALUE1,
                VALUE2
            }
        """.trimIndent()

        val findings = EnumKeepAnnotation(Config.empty).compileAndLintWithContext(env, code)

        findings shouldHaveSize 0
    }
}
