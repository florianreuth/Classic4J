plugins {
    `java-library`
    id("base.java")
    id("base.maven_publish")
    id("publishing.reposilite")
    id("publishing.maven_central")
}

dependencies {
    compileOnly(libs.gson)
}
