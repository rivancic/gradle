# Binary Plugin - Tests

In this project I will show you how you can test every aspect of your binary plugin. In order that we can focus on 
different abstraction of the code we are testing, we can rely on following tests:
- Unit
- Integration
- Functional

Project is using separated **unit** and **functional** tests that is supported by [`jvm-test-suite` plugin](https://docs.gradle.org/current/userguide/jvm_test_suite_plugin.html).

## Unit Tests

In this step you are testing the smallest unit in your plugin. In our case this is an isolated class or even a single method of the class.
There is no dependency on any external components. In Java world JUnit5 Framework is quite popular, and I will use it for 
demonstration.

Unit tests can be located in the `src/test/java` directory.

We aren't testing directly any Gradle task as tasks can't be initialized on their own. We will be able to test tasks in 
functional tests setting.

## Functional Tests

With functional tests we want to test the whole functionality of our plugin. We aren't interested in a details and treat 
plugin implementation as a black box. We are interested that all the individual components of the plugin work as 
expected when applied to a project and try to simulate real project with user configuration. Gradle offers **TestKit** with 
which we can execute Gradle on a test project. 

All functional tests can be found under `src/functionalTest` directory. 

### TestKit

Functional test is executed with the help of TestKit. It can execute build with the `GradleRunner` class. To be able to use it you have 
to add `gradleTestKit()` to your plugin test dependencies. When the build executes you can also investigate the build output.

Logs of the build can be gathered through the `BuildResult.getOutput()` method.

GradleRunner snippets:

```java
@TempDir
File testProjectDir;

// Build GradleRunner
BuildResult result = GradleRunner.create()
  .withProjectDir(testProjectDir)
  .withArguments("taskName")
  .withPluginClasspath()
  .build();

// Assertions
Assertions.assertEquals(SUCCESS, result.task(":taskName").getOutcome());
Assertions.assertTrue(result.getOutput().contains("Text in Gradle logs"));
```

### Description of tests:

|    Module     |  Description  |
| ------------- |:-------------:|
| [CleanTaskTest](src/functionalTest/java/com/rivancic/gradle/plugin/files/tasks/clean/CleanTaskTest.java) | Test that `clean` task removes build directory. |
| [SortFilesTaskTest](src/functionalTest/java/com/rivancic/gradle/plugin/files/tasks/sort/SortFilesTaskTest.java) | Test that `sortFiles` task sorts files from the original source to `build/files` folder. It's parametrized, will test multiple different configurations. |
| [SortFilesMissingInputTest](src/functionalTest/java/com/rivancic/gradle/plugin/files/tasks/sort/SortFilesMissingInputTest.java) | Test that `sortFiles` task fails if input directory is missing. |
| [SortFilesTaskResourcesTest](src/functionalTest/java/com/rivancic/gradle/plugin/files/tasks/sort/SortFilesTaskResourcesTest.java) | Test uses files in `resources` folder that define the project, instead of programmatically creating them. Can make test code more compact. |
| [SortFilesTaskUpToDateTest](src/functionalTest/java/com/rivancic/gradle/plugin/files/tasks/sort/SortFilesTaskUpToDateTest.java) | Test that makes sure `sortFiles` task is implemented correctly and will be skipped if executed for the second time. When the task is executed with same inputs the task outcome should be `UP_TO_DATE`. |
| [SortFilesTaskWrongSortingTypeTest](src/functionalTest/java/com/rivancic/gradle/plugin/files/tasks/sort/SortFilesTaskWrongSortingTypeTest.java) | Test that verifies how plugin behaves when wrong `sortType` is defined in the extension. |

I could go even deeper and really try to implement many edge cases for example test sorting with creation dates. I will
skip those for brevity of the presentation, but will add additional tests in case of any issues.

## Resources

[Testing Build Logic with TestKit (Gradle Userguide)](https://docs.gradle.org/current/userguide/test_kit.html)

[The JVM Test Suite Plugin (Gradle Userguide)](https://docs.gradle.org/current/userguide/jvm_test_suite_plugin.html)

<!---
### Examples

// TODO add some github examples of real life projects that are defining extensions and the projects that are then actually using those.

Source code of java-gradle-plugin 
https://github.com/gradle/gradle/blob/e2b7808f890e9fec75cf7215466afb2c3170d709/subprojects/plugin-development/src/main/java/org/gradle/plugin/devel/plugins/JavaGradlePluginPlugin.java


-->


