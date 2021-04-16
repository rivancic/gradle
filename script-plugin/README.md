# Gradle Script Plugin

This directory contains assignments and examples with the focus on Gradle script plugins.

|    Module     |  Description  |  Resources   |
| ------------- |:-------------:|-------------:|
| **[simple-script-plugin](simple-script-plugin)**     | Extraction of files tasks in a separate file. | Simple script plugin  |
| **[remote-script-plugin](remote-script-plugin)** | Script plugin is being fetched from remote location. | Remote script plugin |
| **[remote-versioned-script-plugin](remote-versioned-script-plugin)** | Script plugin is being fetched from remote versioned repository. One can define the version of plugin being applied. | Remote versioned script plugin |
| **Modularity** | When your build script or script plugin starts to aggregate tasks and configurations with different responsibilities its time to split them up for easier maintenance and re-usability. Below you will see two different projects applying a sub group of script plugins. Only the ones are applied that are needed for a particular build. | Remote versioned modular script plugin |
| **[remote-versioned-modular-script-plugin-library](remote-versioned-modular-script-plugin-library)** | Contains plain Java library example that is applying remote script plugin assembled out of multiple plugins that are covering: <ul><li>compilation</li><li>unit testing</li></ul> |  |
| **[remote-versioned-modular-script-plugin-application](remote-versioned-modular-script-plugin-application)** | Contains Spring Boot application example that is applying remote script plugin assembled out of multiple plugins that are covering: <ul><li>compilation</li><li>unit testing</li><li>integration testing</li><li>frameworks</li></ul> |  |

