plugins {
    kotlin("multiplatform") version "1.5.10"
}

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser()
    }
    jvm()

    sourceSets {
        commonTest {
            dependencies {
                api(kotlin("test"))
            }
        }
    }
}
