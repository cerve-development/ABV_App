plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.koin.core)
            implementation(libs.datastore.preferences)
            implementation(libs.kotlinx.datetime)
            implementation("io.github.murzagalin:multiplatform-expressions-evaluator:1.0.0")
        }
        commonTest.dependencies {
//            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            api(libs.androidx.startup)
        }
    }
}

android {
    namespace = "com.cerve.abv.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}
