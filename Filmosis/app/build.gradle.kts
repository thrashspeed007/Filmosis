import org.jetbrains.dokka.DokkaConfiguration

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.filmosis"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.filmosis"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    tasks.dokkaHtml.configure {
        suppressInheritedMembers.set(true)
    }
}

tasks.dokkaHtml.configure {
    dokkaSourceSets {
        configureEach {
            // A set of visibility modifiers that should be documented
            // If set by user, overrides includeNonPublic. Default is PUBLIC
            documentedVisibilities.set(listOf(DokkaConfiguration.Visibility.PRIVATE) + documentedVisibilities.get())


            includeNonPublic.set(true)
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    debugImplementation("androidx.fragment:fragment-testing:1.6.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation ("com.google.android.material:material:1.11.0")

    // Retrofit
    // https://square.github.io/retrofit/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson (Retrofit version)
    // https://github.com/google/gson
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // CarouselRecyclerView
    // https://github.com/sparrow007/CarouselRecyclerview
    implementation ("com.github.sparrow007:carouselrecyclerview:1.2.6")

    // CircleImage
    //https://github.com/hdodenhof/CircleImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Dokka
    // https://github.com/Kotlin/dokka
    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:1.9.10")


    // Glide
    // https://github.com/bumptech/glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    // librerias de Basefuego
    // https://firebase.google.com/docs/android/setup#available-libraries

    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))

    // Firebase analytics
    implementation("com.google.firebase:firebase-analytics")

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth")

    // Firebase Authentication with Play Services
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

    //Firebase Storage
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")

    //okhttp
    implementation("com.squareup.okhttp3:okhttp:4.9.2")

    //Material Cardview
    implementation("com.google.android.material:material:1.11.0")

    //Pruebas unitaruas
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.4.2")
    testImplementation("org.mockito:mockito-core:3.12.4")
    androidTestImplementation("com.google.truth:truth:1.4.2")

}
