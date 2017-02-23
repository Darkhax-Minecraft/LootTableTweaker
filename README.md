# LootTableTweaker [![](http://cf.way2muchnoise.eu/261339.svg)](https://minecraft.curseforge.com/projects/loottabletweaker) [![](http://cf.way2muchnoise.eu/versions/261339.svg)](https://minecraft.curseforge.com/projects/loottabletweaker)
A special utility mod for Minecraft. Adds a bunch of new content, and has addon support for many other mods!

##Source Code
The latest source code can be found here on [GitHub](https://github.com/Darkhax-Minecraft/LootTableTweaker). If you are using Git, you can use the following command to clone the project: `git clone git:github.com/Darkhax-Minecraft/LootTableTweaker.git`

##Building from Source
This project can be built using the Gradle Wrapper included in the project. When the 'gradlew build' command is executed, a compiled JAR will be created in `~/build/libs`. Sources and Javadocs will also be generated in the same directory. Alternatively the latest builds of Dark-Utilities along with Sources and Javadocs can be found [here](http://maven.epoxide.org/net/darkhax/lttweaker/LootTableTweaker/).

##Contributing
This project is open to contributions. If you would like to report an issue, create a pull request, offer advice or provide translations for other languages, they would be more than welcome.

##Dependency Management
If you are using [Gradle](https://gradle.org) to manage your dependencies, add the following into your `build.gradle` file. Make sure to update the version from time to time.
```
repositories {

    maven { url 'http://maven.epoxide.org' }
}

dependencies {

    compile "net.darkhax.lttweaker:LootTableTweaker:1.10.2-1.0.0.2"
}
```

##Credits
* [Darkhax](https://github.com/darkhax) - Lead developer of the mod.
