# Binary Plugin Assignment Done

## Implementation

This project shows you one possible solution for the Binary Plugin Assignment:
- [Binary Plugin Assignment - Gradle Plugin Development](https://www.udemy.com/course/gradle-development/learn/practice/1361294)

Solution for the assignment is taking over the functionality from Precompiled Plugin Assignment.
Code is written in Java language.
Plugin logic is broken down in two tasks: 
- [SortFilesTask](src/main/java/com/rivancic/gradle/plugin/files/tasks/sort/SortFilesTask.java) and 
- [DeleteTask](src/main/java/com/rivancic/gradle/plugin/files/tasks/delete/DeleteTask.java)

While sorting algorithm is extracted further into [FileDirectoryMapper](src/main/java/com/rivancic/gradle/plugin/files/tasks/sort/mapper/FileDirectoryMapper.java) implementation classes.

In [build.gradle](build.gradle) plugin ID: `com.rivancic.files-plugin` is defined within the `gradlePlugin{}` block with the help of `java-gradle-plugin`.
implementationClass property points to `com.rivancic.gradle.plugin.files.FilesPlugin`.

## Testing

For testing the solution you can publish plugin with the `maven-publish` plugin to local Maven repository and then apply
it in the testing project as:
```
plugins {
  id "com.rivancic.files-plugin" version "0.1.0"
}
```

I have created testing project just for this purpose in [binary-plugin-assignment-test-project](../binary-plugin-assignment-test-project)

## Resources

[Gradle Plugin Development Plugin (Gradle Userguide)](https://docs.gradle.org/current/userguide/java_gradle_plugin.html)

[Developing Custom Gradle Plugins (Gradle Userguide)](https://docs.gradle.org/current/userguide/custom_plugins.html)
