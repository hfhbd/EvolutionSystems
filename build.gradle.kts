plugins {
    kotlin("multiplatform") version "1.8.10"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
