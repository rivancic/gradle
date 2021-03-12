# Gradle build tool example projects

This repository contains examples of various Gradle features structured in modules

|    Module     |  Description  |  Resources   |
| ------------- |:-------------:|-------------:|
| **file-tasks-assignment**     | Implementation of two Gradle tasks which can modify files in specific directory | Task resource  |
| **file-tasks-assignment-done** | Same tasks that are implemented are extracted to script plugin. The easiest way of extracting tasks. | Script plugin resource |
| **apply-precompiled-script-plugin**  | Tasks extracted to precompiled script plugin. They are written in Gradle script files. They are compiled into class files. | [Gradle Precompile Script Plugin User-guide](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins) |
| **apply-precompiled-script-plugin** | An example of applying precompiled plugin to a module | [Gradle Precompile Script Plugin User-guide](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins) | 
| **remote-script=plugin** | Contains Gradle script with one task that will be used in script-plugin from remote source | Script plugin resource |

**Precompiled plugin resources**

- https://docs.gradle.org/current/samples/sample_convention_plugins.html
- https://docs.gradle.org/current/userguide/custom_plugins.html
- https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#organizing_gradle_projects