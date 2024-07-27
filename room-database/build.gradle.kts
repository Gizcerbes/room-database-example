import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

kotlin {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    jvm()

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

    sourceSets.commonMain{
        kotlin.srcDir("build/generated/ksp/metadata")
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.sqlite)
        }
    }

}

android {
    namespace = "org.example.project.database"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    buildTypes {

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspCommonMainMetadata", libs.androidx.room.compiler)
}

tasks.named("build") {
    dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
}

tasks.withType<KotlinNativeCompile>().configureEach {
    dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
}

tasks.named("compileKotlinJvm") {
    dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
}

tasks.named("jvmSourcesJar") {
    dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
}




