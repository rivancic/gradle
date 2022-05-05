# Binary Plugin Extension

## Implementation

Till now configuration for the plugins was done through setting the properties in `gradle.properties` file.

If we follow the guidelines of Gradle, we should extend the Gradle DSL when our plugin will be applied with our own extension.
We need to be able to configure 2 parameters in the plugin. For this we will create extension object that holds two properties.
One property will hold files directory and second one the sorting type.

When applying the plugin default extension will be defined through `sortFiles` extension:

```groovy
sortFiles {
  sortType = "date"
  directoryLocation = "files"
}
```

You are still able to read properties in build.gradle file and pass them through the `sortFiles` extension while the
plugin won't read those any properties directly.

## Testing

I have added [binary-plugin-extension-test-project](../binary-plugin-extension-test-project) project where this binary plugin with extension definition is being 
applied and parameters configured through the extension.

## Resources

[Capturing user input to configure plugin runtime behavior (Gradle Userguide)](https://docs.gradle.org/current/userguide/implementing_gradle_plugins.html#capturing_user_input_to_configure_plugin_runtime_behavior)

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