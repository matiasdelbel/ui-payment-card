rootProject.name = "ui-payment-card"

pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()

        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

/* Modules in the project */
include(":app")
include(":payment-card")
