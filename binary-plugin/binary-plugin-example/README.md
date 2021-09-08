# Binary Plugin Example

This project serves as a template for Gradle binary plugin, it contains minimal content to compile it. It also contains
source code for following lecture [Gradle Plugin Development - Binary Plugin](https://www.udemy.com/course/gradle-development/learn/lecture/28264772#overview).

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


## Resources

[Gradle Plugin Development Plugin (Gradle Userguide)](https://docs.gradle.org/current/userguide/java_gradle_plugin.html)

[Developing Custom Gradle Plugins (Gradle Userguide)](https://docs.gradle.org/current/userguide/java_gradle_plugin.html)