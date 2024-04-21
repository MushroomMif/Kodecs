# Kodecs
Small and simple library to simplify work with [DFU](https://github.com/Mojang/DataFixerUpper)
codecs using Kotlin. It provides easy builder for "record-based" codecs, conversion from 
DFU DataResult to Kotlin Result and vice versa and some extension functions.

# How to use this library
To use this library, clone the repository and type `gradlew publishToMavenLocal` in terminal.
Then add maven local repository and library to your build.gradle(.kts)
```gradle
repositories {
    mavenLocal()
}

dependencies {
    implementation("me.mushroommif:kodecs:1.0")
}
```