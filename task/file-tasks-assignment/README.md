# File Tasks Assignment

## Goal 

The main goal of this assignment is that you revisit your knowledge about task creation and managing files within 
a task action. It's expected that you create two custom Gradle tasks, written in build.gradle script.

Big part of build automation tool like Gradle is to manage source code and generated files. For this reason I would like that
 you get familiar with managing files and directories. 

Remember we always want to keep Gradle tasks neatly organized so users will understand the intention of our tasks and 
will be able to find them quickly next time. We can help users with grouping and writing appropriate 
description for each of the tasks.

### 1) sortFiles task

* Task with the name **sortFiles** has to sort all the files in the provided directory. 
* Directory relative path is provided in the gradle.properties file under following key: **tasks.files.folder**
* Sorting has to be executed in such a way that a new directory is created inside the provided directory based on the 
file name extensions of the files. 
* For example if in the provided directory there is a file named image.jpg, then your build script has to create a 
new jpg directory inside provided directory and move image.jpg file inside of it.


Example of provided unsorted files

![Unsorted files](readme-content/unsorted-files.png)

After one executes `graldew sortedFiles` command every file from the root of provided file has to be stored in a subfolder based on the file name extension as in the example below:

![Unsorted files](readme-content/sorted-files.png)

### 2) unSortFiles task

Task has to do the opposite of the sortFiles task. When files are sorted in subdirectories with the extension name if 
one runs `gradlew unSortFiles` then the result should be that all files form provided directory are again located at 
the root of it and all subfolders have to be deleted.

### Additional requirements

Both tasks have to be grouped under files tasks and they have to have appropriate description.
If you run `gradlew tasks` above tasks have to be show as follows.

```
rivancic:file-tasks-assignment rivancic$ ./gradlew tasks

> Task :tasks

------------------------------------------------------------
Tasks runnable from root project 'file-tasks-assignment'
------------------------------------------------------------

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
wrapper - Generates Gradle wrapper files.

Files tasks
-----------
sortFiles - Sorts files in given directory into subdirectories based on their file extensions
unSortFiles - Reverts sorting of files; moves all files back to root of the given directory

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in root project 'file-tasks-assignment'.
dependencies - Displays all dependencies declared in root project 'file-tasks-assignment'.
dependencyInsight - Displays the insight into a specific dependency in root project 'file-tasks-assignment'.
```

## Tips

**General**

- Make sure you have IntelliJ IDEA properly set up, so you will have autocompletion available, you'll be able to investigate 
source code of Gradle API and debug build.grade script. Check following lecture to find out more !!! TODO link to lecture !!!
- You can use logger instance to print messages !!! TODO link to logger section !!!
- You can read gradle project properties from project.ext !!! TODO link to project section !!!

**Tasks**

- With the [Task#doLast()](https://docs.gradle.org/current/dsl/org.gradle.api.Task.html#org.gradle.api.Task:doLast(groovy.lang.Closure)) convenience method you can specify Gradle action for the task, that will run when the task will be executed during execution phase.

**Managing**

- Method [Project#file()](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:file(java.lang.Object)) gets you relative path to the file from project location !!! TODO link to project section !!!
- Method [Project#mkdir()](https://docs.gradle.org/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:mkdir(java.lang.Object)) can create new directory [Gradle Creating directories](https://docs.gradle.org/current/userguide/working_with_files.html#sec:creating_directories_example)
- [java.nio.Files.move()](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/nio/file/Files.html#move(java.nio.file.Path,java.nio.file.Path,java.nio.file.CopyOption...)) can move file from source to target directory
- You can use all other helper classes from Java [java.io](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/io/package-summary.html) and [java.nio.file](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/nio/file/package-summary.html) packages to manage files and folders.

