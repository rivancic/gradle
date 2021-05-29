# Precompiled Script Plugin

Till now all the build logic was contained in the build script. Precompiled script plugin allows you to package whole
build script written in DSL language as in Groovy or Kotlin to package it as a plugin from buildSrc directory.
It will be compiled into class files and packaged into a jar. For all intents and purposes, they are binary plugins and 
can be applied by plugin ID, tested and published as binary plugins.

The benefit you will now receive is that build logic can be extracted step by step in stand-alone classes inside the 
buildSrc directory. In the same way new build logic can be added. This can be an intermediate step at rewriting script logic into 
precompiled or stand-alone binary plugin.

In this project we will build on top of [previous simple script plugin](../../script-plugin/simple-script-plugin) We will transfer build
logic into separate module. Plugin will still be defined in a script file. To create an actual plugin out of build script under buildSrc we
have to add [groovy-gradle-plugin](https://plugins.gradle.org/plugin/dev.gradleplugins.groovy-gradle-plugin) to buildSrc itself.

## Code structure

Script for the plugin has to be located under `buildScr/main/groovy` or `buildSrc/main/kotlin` package.
The name of the plugin by which you will be able to reference it will be the same as the filename of the script itself
without the extension. In case that the filename is **precompiled-script-plugin.gradle** Then the
plugin id will be filename without the extension **.gradle**.

- Filename: precompiled-script-plugin.gradle
- Plugin id: precompiled-script-plugin

<p width="100%">
  <img src="readme-content/gradle-precompiled-script-plugin.png" alt="Gradle precompiled script plugin" width="400">
</p>

Gradle script defined in buildSrc will be available to all the build scripts inside the main project as a plugin.

## Components of the build script plugin

### Project 
  
Project itself is applying plugin that is compiled in buildSrc from the script and made available for all the projects. 

### buildSrc

Containing Gradle script that will be compiled as a plugin. This will be done with the help of groovy-gradle-plugin.
Content will be compiled and result persisted in internal build folder as a buildSrc.jar.
  