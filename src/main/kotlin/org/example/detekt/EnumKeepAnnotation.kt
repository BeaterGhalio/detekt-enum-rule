package org.example.detekt

import io.gitlab.arturbosch.detekt.api.*
import io.gitlab.arturbosch.detekt.rules.hasAnnotation
import org.jetbrains.kotlin.psi.KtClass

class EnumKeepAnnotation(config: Config) : Rule(config) {

    override val issue = Issue(
        javaClass.simpleName,
        Severity.Style,
        "Enums should have @Keep annotation",
        Debt.FIVE_MINS
    )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)

        if (klass.isEnum() && !klass.hasAnnotation("Keep")) {
            report(
                CodeSmell(
                    issue,
                    Entity.atName(klass),
                    "Enum class '${klass.name}' should be annotated with @Keep."
                )
            )
        }
    }
}
