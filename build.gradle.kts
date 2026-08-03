import de.florianreuth.baseproject.*

plugins {
    `java-library`
    id("de.florianreuth.baseproject")
}

setupProject()
setupPublishing()

dependencies {
    compileOnly("com.google.code.gson:gson:2.14.0")
}
