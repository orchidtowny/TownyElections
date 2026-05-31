plugins {
    kotlin("jvm") version "2.3.21"
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "site.remlit.blueb"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.glaremasters.me/repository/towny/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    // supplied by server
    compileOnly("io.papermc.paper:paper-api:1.20-R0.1-SNAPSHOT")

    // supplied by plugins
    compileOnly("com.palmergames.bukkit.towny:towny:0.101.2.0")
    compileOnly("me.clip:placeholderapi:2.11.6")

    // supplied by PluginLoader
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    compileOnly("org.spongepowered:configurate-yaml:4.2.0")
    compileOnly("org.spongepowered:configurate-extra-kotlin:4.2.0")

    compileOnly("org.hibernate.orm:hibernate-core:7.4.0.Final")
    compileOnly("org.hibernate.orm:hibernate-hikaricp:7.4.0.Final")
    compileOnly("jakarta.transaction:transaction-api:2.0.1")

    compileOnly("org.postgresql:postgresql:42.7.11")
    compileOnly("com.mysql:mysql-connector-j:9.7.0")
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21.4")
    }
}

kotlin {
    jvmToolchain(21)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
