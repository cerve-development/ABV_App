plugins {
    id ("com.android.application")
    id ("kotlin-kapt")
    id ("kotlin-android")
    id ("org.jmailen.kotlinter")
    id("com.google.gms.google-services")
//    alias(libs.plugins.crashlytics)
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.fair.tool_belt_abv"

    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        versionCode = 15
        versionName = "1.9.$versionCode"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.version.get()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }

    buildFeatures {
        compose = true
    }

}

dependencies {

    implementation(project(":shared"))
    //activity
    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.runtime.compose)

    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.analytics.ktx)

    //navigation
    implementation(libs.navigation.compose)

    //di
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)

    //in-app-review
    implementation(libs.review.ktx)

    //material design
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.tooling)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.icons.ext)

    //design system
    implementation(libs.cerve.ui.library)

    //splash screen
    implementation(libs.core.splashscreen)

    //testing
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    androidTestImplementation(libs.espresso.core)

}

kotlinter {
    ignoreFailures = false
    disabledRules = arrayOf("package-name", "twitter-compose:unstable-collections")
}
