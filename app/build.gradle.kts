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
        versionName = "1.0.070626" // 1.0.070626 bug increase date, feature increase code

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}