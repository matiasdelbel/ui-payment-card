plugins {
    `kotlin-dsl`
}

group = "com.dbel.gradle.plugins"
sourceSets["main"].java.srcDirs("src/main/kotlin-extensions")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Plugins
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    implementation("com.android.tools.build:gradle:7.0.4")
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
}

gradlePlugin {
    plugins {
        /* Set up the basic configuration for an android module. */
        register("com.dbel.android.library") {
            id = "com.dbel.android.library"
            implementationClass = "com.dbel.gradle.AndroidLibraryPlugin"
        }
        /* Set up the basic configuration for an android compose module. */
        register("com.dbel.android.library.compose") {
            id = "com.dbel.android.library.compose"
            implementationClass = "com.dbel.gradle.ComposeLibraryPlugin"
        }
    }
}