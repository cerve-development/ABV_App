buildscript {

    dependencies {
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.ktlint)
    }

}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotliner) apply false
    alias(libs.plugins.google.services) apply false
}