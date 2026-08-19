plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")  version "2.2.10-2.0.2"
}

    android {
        namespace = "com.example.prepmate"
        compileSdk = 37


        defaultConfig {
            applicationId = "com.example.prepmate"
            minSdk = 24
            targetSdk = 36
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                optimization {
                    enable = false
                }
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    dependencies {
        implementation("com.facebook.android:facebook-login:latest.release")
        implementation("com.google.android.gms:play-services-auth:21.2.0")
        implementation("com.google.firebase:firebase-auth")
        implementation(platform("com.google.firebase:firebase-bom:34.17.0"))
        implementation("com.airbnb.android:lottie:6.3.0")
        implementation("com.google.android.material:material:1.11.0")
        val nav_version = "2.7.7"
        implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
        implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
        implementation(libs.androidx.activity.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.cardview)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.recyclerview)
        implementation(libs.material)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(libs.androidx.junit)
        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.retrofit2:converter-gson:2.11.0")
        implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")

        implementation("io.reactivex.rxjava3:rxjava:3.1.10")
        implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
        implementation("com.github.bumptech.glide:glide:4.16.0")
        implementation("androidx.recyclerview:recyclerview:1.3.2")

        implementation("androidx.room:room-runtime:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")
        ksp("androidx.room:room-compiler:2.6.1")



    }
