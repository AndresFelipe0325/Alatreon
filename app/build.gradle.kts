plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin") //Apply the Safe Args plugin
}

android {
    namespace = "com.andrew.alatreon"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.andrew.alatreon"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Fragment
    implementation(libs.androidx.navigation.fragment.ktx)
    //Navigation
    implementation(libs.androidx.navigation.ui.ktx)
    //Interceptor
    implementation(libs.logging.interceptor)
    //SwipeRefreshLayout
    implementation(libs.androidx.swiperefreshlayout)
    //RecyclerView
    implementation(libs.recyclerview.v7)
    implementation(libs.androidx.recyclerview)
    //Glide
    implementation(libs.glide)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava2)
}