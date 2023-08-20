plugins {
    kotlin("jvm") version "1.6.10"
    `maven-publish`
}

group = "org.example.detekt"
version = "1.1"

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-api:1.23.1")

    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:1.22.0")
    testImplementation(kotlin("test-junit5", "1.5.31"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("io.kotest:kotest-assertions-core:5.4.0")
}


tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_class")
    systemProperty("compile-snippet-tests", project.hasProperty("compile-test-snippets"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
