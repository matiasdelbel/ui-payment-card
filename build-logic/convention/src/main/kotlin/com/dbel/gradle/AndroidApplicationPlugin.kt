package com.dbel.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.dbel.gradle.plugin.kotlinOptions
import org.gradle.api.JavaVersion

@Suppress(names = ["UnstableApiUsage"])
class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android") // TODO version catalog
            }

            extensions.configure<BaseAppModuleExtension> {
                buildToolsVersion = "33.0.0"
                compileSdk = 33

                defaultConfig {  minSdk = 24 }

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
}