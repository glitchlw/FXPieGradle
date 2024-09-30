plugins {
  java
  application
  id("org.openjfx.javafxplugin") version "0.1.0"
}

group="de.medieninformatik"
version="1.0-SNAPSHOT"
val javaVersion = JavaVersion.VERSION_21 // enum

repositories {
  mavenCentral()
}

java {
  setSourceCompatibility(javaVersion)
  setTargetCompatibility(javaVersion)
}

tasks.withType<JavaCompile>().configureEach {
  options.encoding = "UTF-8"
}

application {
  mainModule.set("${group}.${rootProject.name}")
  mainClass.set("${mainModule.get().lowercase()}.Main")
}

javafx {
  setVersion(javaVersion.majorVersion)
  modules("javafx.controls", "javafx.fxml")
}

dependencies {
  val junitVersion: String = "5.10.3"
  implementation(
    "org.openjfx:javafx-controls:17.0.2")
  implementation(
    "org.openjfx:javafx-fxml:17.0.2")
  testImplementation(
    platform("org.junit:junit-bom:${junitVersion}"))
  testImplementation(
    "org.junit.jupiter:junit-jupiter:${junitVersion}")
  implementation(project(":Model"))
}

tasks.test {
  useJUnitPlatform()
}

tasks.named<JavaExec>("run") {
  setIgnoreExitValue(true)
}