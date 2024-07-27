This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

[![Everything Is AWESOME](https://github.com/user-attachments/assets/d1647e3d-4172-4951-8cf7-175f7a3afb82)](https://youtu.be/nQh08gP01Uk)



## To set up a room database you need.
### 1. Add dependencies and plugins
```
[versions]
...
ksp = "2.0.0-1.0.23" // it depends on the kotlin's version
room = "2.7.0-alpha05"
sqlite = "2.5.0-alpha05"

[libraries]
...
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
sqlite-bundled = { module = "androidx.sqlite:sqlite-bundled", version.ref = "sqlite" }
sqlite = { module = "androidx.sqlite:sqlite", version.ref = "sqlite" }

[plugins]
...
room = { id = "androidx.room", version.ref = "room" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
```
### 2. Add plugins in root gradle.kts
```
    alias(libs.plugins.room) apply false
    alias(libs.plugins.ksp) apply false
```

### 3. Create new module and configure ${YOUR_MODULE}/build.gradle.kts
3.1 plugins
```
plugins {
    alias(libs.plugins.kotlinMultiplatform) // for using library with multiplatform
    alias(libs.plugins.androidLibrary) // for declarate it as Android library
    alias(libs.plugins.room) // Room Database
    alias(libs.plugins.ksp) // Kotlin symbol processing
}
```
3.2 Add supported platforms 
```
kotlin {
...
  androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
...
}
```
3.3 Configure android library
```
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
```
3.4 Add directory for schemas
```
room {
    schemaDirectory("$projectDir/schemas")
}
```
3.5 Add dependensies for root
```
dependencies {
    add("kspCommonMainMetadata", libs.androidx.room.compiler)
}
```
3.6 Create some tasks
```
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
```
3.7 Mark directory to use generated code
```
kotlin {
  ...
   sourceSets.commonMain{
        kotlin.srcDir("build/generated/ksp/metadata")
   }
  ...
}
```
3.8 Add dependency for library
```
kotlin {
   ...
   sourceSets {
        commonMain.dependencies {
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.sqlite)
        }
   }
   ...
}
```
3.9 Create directories for project
```
srs/androidMain/kotlin
srs/commonMain/kotlin
srs/iosMain/kotlin
srs/jvmMain/kotlin
```

