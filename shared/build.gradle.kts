plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.koin.core)
            implementation(libs.datastore.preferences)
            implementation(libs.kotlinx.datetime)
            implementation(libs.expressions.evaluator)

            with(libs.sqldelight) {
                implementation(coroutines)
                implementation(runtime)
            }

        }
        androidMain.dependencies {
            implementation(libs.sqldelight.android)
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

sqldelight {
    databases {
        create("SharedDatabase") {
            packageName.set("com.cerve.co.abv.shared.cache")
        }
    }
}
