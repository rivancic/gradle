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

### Examples

Public GitHub examples using precompiled Gradle script plugins:

| Project  | Description |
| ------------- | ------------- |
| **<a href="https://github.com/co-cddo/federated-api-model" target="_blank">federated-api-model (buildSrc)</a>** | This project uses 2 convention precompiled plugins packaged in <a href="https://github.com/co-cddo/federated-api-model/tree/main/examples/java/buildSrc/src/main/groovy" target="_blank">buildSrc directory</a>. First **common-conventions** plugin applies and configures java and jacoco plugin. While the second **spring-boot-conventions** plugin applies the first one and additionally sets up exclusion configuration for loggers and testing engine. Then spring-boot-conventions is used in an <a href="https://github.com/co-cddo/federated-api-model/tree/main/examples/java" target="_blank">example project</a>.   |
| **<a href="https://github.com/ThoughtWorks-DPS/dps-starter-boot" target="_blank">starter-boot (buildSrc)</a>** | The project contains multiple Spring Boot starter like projects. I don't know how they are included in the builds, maybe as composed builds. Nonetheless they are interesting as they have defined and use dozen (More than 50!) of precompiled plugins defined in buildSrc folder. Their purpose is briefly <a href="https://github.com/ThoughtWorks-DPS/dps-starter-boot/blob/main/buildSrc/README.md" target="_blank">described here</a>.  Actual usage of convention plugins is shown in an <a href="https://github.com/ThoughtWorks-DPS/dps-starter-boot/blob/main/test-example/" target="_blank">test-example project</a> |
| **<a href="https://github.com/QuiltMC/gradle-convention-plugins" target="_blank">QuiltMC (standalone)</a>** | Unlike projects above this one doesn't include precompiled plugin in buildSrc folder, but it defines precompiled plugin as a **standalone project**. Plugin gets compiled and packaged as a binary plugin in a jar file. To be actually used in other projects it needs to be published to some repository or being applied as a composite build. Actual use of plugin can be found in another precompiled plugin <a href="https://github.com/QuiltMC/quilt-standard-libraries/blob/37f6c8848e627bae4fe0653292a3f161d5f563a0/build-logic/src/main/groovy/qsl.module.gradle#L25" target="_blank">qsl.module</a>   |

<!-- Also need to add spring sse redis composite build precompiled script later
https://rivancic.atlassian.net/wiki/spaces/GUC/pages/800358401/4+2+Precompiled+Gradle+Plugin+GitHub+example
https://github.com/vpavic/samples-spring-sse-redis/blob/main/settings.gradle
--> 