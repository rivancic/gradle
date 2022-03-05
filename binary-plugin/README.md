# Gradle Binary Plugin

This directory contains assignments and examples with the focus on standalone Gradle binary plugins.

|    Module     |  Description  |  Lectures   |
| ------------- |:-------------:|-------------:|
| **[binary-plugin-example](binary-plugin-example)** | Example showing necessary components of binary plugin, utilities that `gradle-java-plugin` plugin offers to ease up binary plugin compilation and packaging. Publishing of plugin with `maven-publish` plugin to local, private/remote Maven repository or [Gradle Plugin Portal](https://plugins.gradle.org/) is also covered. | [Binary Plugin](https://www.udemy.com/course/gradle-development/learn/lecture/28264772#overview) <br> <br> [Publishing - Local Maven Repository](https://www.udemy.com/course/gradle-development/learn/lecture/29080272#overview) <br> <br> [Publishing - Private Maven Repository](https://www.udemy.com/course/gradle-development/learn/lecture/29429886)|
| **[binary-plugin-assignment-done](binary-plugin-assignment-done)** | Solved assignment for the binary plugin lecture. It shows how can script plugin from the previous lectures for sorting files be overwritten into binary plugin.| [Binary Plugin Assignment](https://www.udemy.com/course/gradle-development/learn/practice/1361294) |
| **[binary-plugin-assignment-test-project](binary-plugin-assignment-test-project)** | Simple project that demonstrates application of the binary plugin created in binary-plugin-assignment-done. | [Binary Plugin Assignment](https://www.udemy.com/course/gradle-development/learn/practice/1361294) |
| **[binary-plugin-gradle-plugin-portal](binary-plugin-gradle-plugin-portal)** | Extended binary plugin from the assignment with the option to upload the plugin to official Gradle Plugin Portal. | [Publishing - Gradle Plugin Portal](https://www.udemy.com/course/gradle-development/learn/practice/1361294) |

# Resources

### Examples

Public GitHub examples using binary Gradle plugins:

| Project  | Description |
| ------------- | ------------- |
| **<a href="https://github.com/gradle-dependency-analyze/gradle-dependency-analyze" target="_blank">gradle-dependency-analyze</a>** | This is really practical binary Gradle plugin for spotting unused dependencies and can be perfect match for writing clean gradle scripts. Uses <a href="https://github.com/gradle-dependency-analyze/gradle-dependency-analyze/blob/master/build.gradle#L3" target="_blank">java-gradle-plugin</a> for developing a plugin, while the source code is written in Groovy. Definition of <a href="https://github.com/gradle-dependency-analyze/gradle-dependency-analyze/blob/master/build.gradle#L33" target="_blank">plugin ID and implementation class</a>. Main <a href="https://github.com/gradle-dependency-analyze/gradle-dependency-analyze/blob/master/src/main/groovy/ca/cutterslade/gradle/analyze/AnalyzeDependenciesPlugin.groovy#L14" target="_blank">AnalyzeDependenciesPlugin Plugin class. |
| **<a href="https://github.com/micronaut-projects/micronaut-gradle-plugin" target="_blank">micronaut-gradle-plugin</a>** | Binary plugin for development of Micronaut applications. There are <a href="https://github.com/micronaut-projects/micronaut-gradle-plugin/blob/master/build.gradle#L28" target="_blank">multiple plugins</a> packed into one artifact. All plugins extend simple <a href="https://github.com/micronaut-projects/micronaut-gradle-plugin/blob/master/src/main/java/io/micronaut/gradle/MicronautBasePlugin.java" target="_blank">MicronautBasePlugin</a>, its whole purpose is to apply Micronaut Plugin specific extension. Other plugins then don't extend this plugin but they apply it themselves in the <a href="https://github.com/micronaut-projects/micronaut-gradle-plugin/blob/master/src/main/java/io/micronaut/gradle/MicronautComponentPlugin.java#L74" target="_blank">apply() method<a>. Source code for the plugin is written in Java. |
