# Gradle build tool example projects

This repository contains examples of various Gradle features with the emphasys on Gradle plugins. 

Below is the menu for easier navigation between sections.

All examples are written using Groovy Gradle build scripts and additional code in Java. Projects were created with IntelliJ IDEA.

|    Module     |  Description  |  Resources   |
| ------------- |:-------------:|-------------:|
| **[task](task/)**     | Implementation of two Gradle tasks which can modify files in specific directory. | [Gradle tasks in-depth](https://docs.gradle.org/current/userguide/more_about_tasks.html)  |
| **[script-plugin](script-plugin/)** | Tasks implemented in build.gradle are extracted to script plugin. Easiest way to build a plugin. | Script plugin resource |
| **[precompiled-script-plugin](precompiled-script-plugin/)**  | Going setp further one can extract script plugin to precompiled script-plugin. | [Gradle Precompile Script Plugin User-guide](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins) |
| **[binary-plugin](binary-plugin/)**  | Not the fastest way to create a plugin but it gives you the most options regarding extraction of logic and reusability. | [Gradle Precompile Script Plugin User-guide](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins) |