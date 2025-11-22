// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Asegura que el worker del plugin de Hilt use la versión correcta
        classpath("com.squareup:javapoet:1.13.0")
    }
    configurations.classpath {
        resolutionStrategy.force("com.squareup:javapoet:1.13.0")
        // (opcional) por si algún plugin trae otra versión

        
    }
}
