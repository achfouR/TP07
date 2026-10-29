plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "br.unisanta.firebase"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.unisanta.firebase"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.firebaseui:firebase-ui-auth:9.0.0")
}
