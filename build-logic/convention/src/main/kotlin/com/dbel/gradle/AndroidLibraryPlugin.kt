package com.dbel.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.dbel.gradle.plugin.android
import com.dbel.gradle.plugin.build
import com.dbel.gradle.plugin.kotlinOptions
import com.dbel.gradle.plugin.plugins
import org.gradle.api.JavaVersion

@Suppress(names = ["UnstableApiUsage"])
class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) = target.build {
        plugins {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        android {
            compileSdk = 33
            buildToolsVersion = "33.0.0"

            defaultConfig {
                targetSdk = 33
                minSdk = 24
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }
}
