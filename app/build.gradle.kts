plugins {
    id ("com.android.application")
    id ("kotlin-kapt")
    id ("kotlin-android")
    id ("org.jmailen.kotlinter")
//    id ("com.google.dagger.hilt.android")
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
}

android {

    defaultConfig {
        applicationId = "com.fair.tool_belt_abv"
        minSdk = 21
        compileSdk = 34
        targetSdk = 34
        versionCode = 14
        versionName = "1.9.$versionCode"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
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

    namespace = "com.fair.tool_belt_abv"

}

dependencies {

    implementation(project(":shared"))
    //activity
    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.runtime.compose)

    //firebase
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.analytics.ktx)

    //navigation
    implementation(libs.navigation.compose)
//    implementation(libs.hilt.navigation.compose)

    //di
    implementation(libs.koin.android)
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")
//    implementation(libs.koin.android.viewmodel)

    // (dagger-hilt)
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)

    //settings
//    implementation(libs.datastore.preferences)

    //in-app-review
    implementation(libs.review.ktx)

    //material design
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-extended")

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
