# Binary Plugin Extension

## Implementation

Till now configuration for the plugins was done through setting the properties in `gradle.properties` file.

If we follow the guidelines of Gradle, we should extend the Gradle DSL when our plugin will be applied with our own extension.
We need to be able to configure 2 parameters in the plugin. For this we will create extension object that holds two properties.
One property will hold files directory and second one the sorting type.

When applying the plugin, `sortFiles` extension will be added to the container of project extensions.
SortFilesTaskExtension is a POJO class holding configuration properties.

```java
SortFilesTaskExtension sortFilesTaskExtension = project.getExtensions().create("sortFiles", SortFilesTaskExtension.class);
```

Later you can reference this extension instance to fetch configuration values for the plugin:

```java
sortFilesTaskExtension.getSortType().isPresent()?sortFilesTaskExtension.getSortType().get():DEFAULT_SORTING_TYPE
```

Simple extension block for sorting files:

```groovy
sortFiles {
  sortType = "date"
  directoryLocation = "files"
}
```

You are still able to read properties in `build.gradle` script and pass them through the `sortFiles` extension, while the
plugin won't read any properties directly:

```groovy
sortFiles {
  sortType = getProject().getExtensions().getExtraProperties().get('tasks.files.sortType')
  directoryLocation = getProject().getExtensions().getExtraProperties().get('tasks.files.folder')
}
```

## Testing

I have added [binary-plugin-extension-test-project](../binary-plugin-extension-test-project) project where this binary plugin with extension definition is being applied and parameters configured through the extension.

## Resources
- [Developing Custom Gradle Plugins - Making the plugin configurable (Gradle Userguide)](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:getting_input_from_the_build)
- [Implementing Gradle plugins - Modeling DSL-like APIs (Gradle Userguide)](https://docs.gradle.org/current/userguide/implementing_gradle_plugins.html#modeling_dsl_like_apis)
- [Implementing Gradle plugins - Capturing user input to configure plugin runtime behavior (Gradle Userguide)](https://docs.gradle.org/current/userguide/implementing_gradle_plugins.html#capturing_user_input_to_configure_plugin_runtime_behavior)

<!--- 
Injecting parameters from extension to task https://stackoverflow.com/questions/70661787/how-should-configuration-be-passed-to-gradle-task-from-a-gradle-extension and write an answer 
--->

<!--- Important, Add to readme in task avoidance not here in plugins, explain 
 Check https://docs.gradle.org/current/userguide/task_configuration_avoidance.html#sec:task_configuration_avoidance_guidelines 
-->

<!---
### Examples

// TODO add some github examples of real life projects that are defining extensions and the projects that are then actually using those.
-->
