# Binary Plugin Example

This project serves as a template for Gradle binary plugin, it contains minimal content to compile it. It also contains
source code for following lectures:
- [Gradle Plugin Development - Binary Plugin](https://www.udemy.com/course/gradle-development/learn/lecture/28264772#overview)
- [Gradle Plugin Development - Publishing - Local Maven Repository](https://www.udemy.com/course/gradle-development/learn/lecture/29080272#overview)

To successfully create a plugin you should:
- Import `java-gradle-plugin` core Gradle plugin
- Create class implementing `Plugin` interface and extending `apply()` method
- Define plugin descriptor in `build.gradle` script file

First of, this is a standalone project with the JVM structure.

## Plugin class

Inside of `src/main/java` directory there is [GreetingPlugin](src/main/java/com/rivancic/gradle/plugin/greeting/GreetingPlugin.java) class implementing Plugin interface. 
In order to be able to refer to Gradle API components like Plugin interface you should apply plugin `java-gradle-plugin`
inside `build.gradle` file.

Plugin will print simple logging statement at the configuration time of the project,
to proof that the plugin is applied.

## Plugin descriptor

In order that plugin descriptor will be generated you have to specify plugins inside the
`gradlePlugin{}` block.

## Publishing

To be able to publish plugin to a Maven repository you have to apply `maven-publish` plugin, which will give you ability
of artifact publication.

### Local Maven Publishing

To publish compiled plugin to Local Maven repository you have to run task `publishToMavenLocal`. As a result this plugin will 
be published to `{user-home}/.m2/repository/com/rivancic/greeting-plugin` directory.

To be able to refenrece this plugin from withing a project you will have to define Local Maven repository inside of
`pluginManagement{}` block in `settings.gradle` file.

### Private Maven Archiva Repository

TODO ... write something.


## Resources

[Gradle Plugin Development Plugin (Gradle Userguide)](https://docs.gradle.org/current/userguide/java_gradle_plugin.html)

TODO fix the link

[Developing Custom Gradle Plugins (Gradle Userguide)](https://docs.gradle.org/current/userguide/java_gradle_plugin.html)