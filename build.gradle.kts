import de.florianreuth.baseproject.*

plugins {
    `java-library`
    id("de.florianreuth.baseproject")
}

setupProject()
setupPublishing()

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.google.code.gson:gson:2.13.2")
}
