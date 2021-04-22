# Script Plugin

This Gradle project shows how the build logic can be extracted out of main build.gradle build script.
You would want to apply this kind of refactoring if build.gradle script contains more than one group of tasks. 
This will be more than likely when you are working on a real world applications.

This example builds on top of previous project that was used for creating [Gradle tasks](../../task/file-tasks-assignment-done).

Check the [build.gradle](build.gradle) script file to see how the plugin is applied.