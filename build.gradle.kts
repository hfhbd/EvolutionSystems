plugins {
    kotlin("multiplatform") version "1.5.0"
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
