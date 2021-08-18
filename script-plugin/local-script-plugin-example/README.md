# Local Script Plugin Example

This Gradle project shows how the build logic can be extracted out of main **build.gradle** build script into local script plugin as described in the [following lecture](https://www.udemy.com/course/gradle-development/learn/lecture/27236536#overview).

You would want to apply this kind of refactoring if **build.gradle** script contains more than one group of tasks.
Even better option is to extract logic to **buildSrc** or if the tasks needs to be reused into **external binary plugin**.
Still in case that you are working on a PoC, your project isn't that complex, or you just don't have time to invest into
proper plugin you can extract build logic to local Gradle script files to separate build logic.

This example builds on top of previous project that was used for creating [Gradle tasks](../../task/file-tasks-assignment-done).

Compared to the task assignment all the logic was extracted to [taskPlugin.gradle](taskPlugin.gradle) file.
Only thing being done in the main [build.gradle](build.gradle) script file is to apply local script plugin taskPlugin.


![Local Script Plugin](readme-content/local-script-plugin.png)

## Gradle build script invocation

### Default

By default, always **build.gradle** in the root of the project will be processed by Gradle. 



### Specify build file in settings.gradle

You can define [project build filename](https://docs.gradle.org/current/javadoc/org/gradle/api/initialization/ProjectDescriptor.html#setBuildFileName-java.lang.String-) in **settings.gradle** file.

**settings.gradle**
```
rootProject.buildFileName = 'taskPlugin.gradle'
```

### Specify build file over an CLI option (Deprecated)

To execute taskPlugin script file directly use following command (Will be deprecated in 8.0)

```
./gradlew sF -b taskPlugin.gradle
```


## Resources

[Script Plugins (Gradle Userguide)](https://docs.gradle.org/current/userguide/plugins.html#sec:script_plugins)

[Gradle CLI - Environment Options (Gradle Userguide)](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:environment_options)

[ProjectDescriptor (Gradle API Documentation)](https://docs.gradle.org/current/javadoc/org/gradle/api/initialization/ProjectDescriptor.html#setBuildFileName-java.lang.String-)
