import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.mocking)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    task("testClasses")

    jvm("desktop")
    
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
        val desktopMain by getting

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
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            api(compose.materialIconsExtended)
            implementation(libs.ktor.core)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.negotiation)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.compose.navigation)
            implementation(libs.androidx.lifecycle.viewmodel.compose)

            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.coil3)
            api(libs.coil3.network)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin.ios)
            implementation(libs.ktor.ios)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
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
            implementation(libs.turbine.turbine)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
dependencies {
    implementation(libs.androidx.security.identity.credential)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.arttachdevs.pokedexkmp"
            packageVersion = "1.0.0"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}