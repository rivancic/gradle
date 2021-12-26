# Gradle Precompiled Plugin

This directory contains examples and assignments with the focus on Gradle precompiled script plugins.

Often precompiled script plugin is used as a plugin reused in one project so that is located in **buildSrc** directory based on the Gradle convention.
But precompiled script plugin can also be used as a standalone plugin in a independent project.

|    Module     |  Description  |  Resources   |
| ------------- |:-------------:|-------------:|
| **[precompiled-script-plugin-assignment-done](precompiled-script-plugin-assignment-done)** | Script plugin transformed into precompiled script plugin. This is supported with **groovy-gradle-plugin** plugin. This way plugin can be reused in multiple subprojects. Gradle components can be extracted into separate classes. | [Precompiled plugin lecture](https://www.udemy.com/course/gradle-development/learn/lecture/27025626) |

## Resources

- [Developing Custom Gradle Plugins - Precompiled script plugins (Gradle Userguide)](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins)
- [Sharing build logic between subprojects Sample (Gradle Samples)](https://docs.gradle.org/current/samples/sample_convention_plugins.html)
- [Use buildSrc to abstract imperative logic (Gradle Userguide)](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)