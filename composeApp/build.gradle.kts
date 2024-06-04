import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.mocking)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    task("testClasses")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {

        androidMain.dependencies {
            api(libs.androidx.activity.compose)
            api(libs.androidx.appcompat)
            api(libs.androidx.core)
            implementation(libs.ktor.okhttp)
            implementation(libs.ktor.android)
            implementation(libs.coil3.network)
            api(libs.koin.android)
            api(libs.koin.core)
            api(libs.koin.compose)
            implementation(libs.system.ui.controller)
            implementation(libs.accompanist.permissions)
        }
        commonMain.dependencies {
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.animation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            api(compose.materialIconsExtended)
            implementation(libs.ktor.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.negotiation)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.compose.navigation)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.sqlite.bundled)

            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.coil3)
            api(libs.coil3.network)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin.ios)
            implementation("co.touchlab:stately-common:2.0.6")
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))

            implementation(libs.kotest.framework.engine)
            implementation(libs.kotest.assertions.core)
            implementation(libs.kotest.property)
            implementation(libs.ktor.mock)
            implementation(libs.coroutines.test)
            implementation(libs.koin.test)

            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
    }
}

android {
    namespace = "com.arttachdevs.pokedexkmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}