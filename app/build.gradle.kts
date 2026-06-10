plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ltd14.cellgate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ltd14.cellgate"
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.070626"

        setProperty("archivesBaseName", "cellgat_${versionName}")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    applicationVariants.all {
        outputs.all {
            val versionName = android.defaultConfig.versionName ?: "unknown"
            (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                "cellgat_${versionName}.apk"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}