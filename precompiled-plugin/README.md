# Gradle Precompiled Plugin 

This directory contains assignments and examples with the focus on precompiled Gradle plugins.

Script or source code for the plugin has to go into **buildSrc** directory based on the Gradle convention.

|    Module     |  Description  |  Resources   |
| ------------- |:-------------:|-------------:|
| **[precompiled-script-plugin](precompiled-script-plugin)** | Script plugin transformed into precompiled script plugin. This is supported with **groovy-gradle-plugin** plugin. This way plugin can be reused in multiple subprojects. Gradle components can be extracted into separate classes. | Precompiled script plugin resource  |
| **[precompiled-plugin](precompiled-plugin)** | Precompiled script plugin transformed into standard precompiled plugin. Now whole part of script plugin is extracted into standalone components. Code is structured as in a standard Java / Groovy / Kotlin application. | Precompiled plugin resource  |

**Precompiled plugin external resources**

- [https://docs.gradle.org/current/samples/sample_convention_plugins.html](https://docs.gradle.org/current/samples/sample_convention_plugins.html)
- [https://docs.gradle.org/current/userguide/custom_plugins.html](https://docs.gradle.org/current/userguide/custom_plugins.html)
- [https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#organizing_gradle_projects](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#organizing_gradle_projects)