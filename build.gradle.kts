plugins {
    kotlin("multiplatform") version "2.0.20"
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
