# Binary Plugin Gradle Plugin Portal

Project with the configuration that can publish binary Gradle plugin to Gradle Plugin Portal.

[Gradle Plugin Portal](https://plugins.gradle.org/), is a centralized, searchable repository dedicated to Gradle plugins.

In order to prepare the project for publishing it to Gradle Plugin Portal you have to make sure you add following configuration:

1) Make sure you have a Gradle Plugin Portal account, which can be set up at a [login/registration page](https://plugins.gradle.org/user/login), and create key and its secret for the API access.
   Alternatively to creation of API keys manually you can later use gradle   login task that will guide you through the process of setting up keys.

2) Save Gradle Plugin Portal key and secret to `~/.gradle/gradle.properties`:
   
```properties
gradle.publish.key=...
gradle.publish.secret=...
```
3) In project apply **com.gradle.plugin-publish** plugin.
   
```groovy
plugins {
  id "java-gradle-plugin"
  id "maven-publish"
  id 'com.gradle.plugin-publish' version '0.20.0'
}
```
4) Verify that you have `description` set in specific `plugins{}` extension block that is of type [PluginDeclaration](https://docs.gradle.org/current/javadoc/org/gradle/plugin/devel/PluginDeclaration.html).
The group part in ID will have to match with your email or user name.
   
```groovy
gradlePlugin {
  plugins {
    create("filesPlugin") {
      id = "com.rivancic.files-plugin"
      displayName = "Files Sorting Plugin"
      description = "Plugin that can sort provided files based on a particular rule (alphabetically, creation date, extension)"
      implementationClass = "com.rivancic.gradle.plugin.files.FilesPlugin"
    }
  }
}
```
5) In `pluginBundle{}` extension you have to add required metadata `website`, `vcsUrl` and `tags` properties for the plugin.

```groovy
pluginBundle {
  website = 'https://github.com/rivancic/gradle'
  vcsUrl = 'https://github.com/rivancic/gradle' // has to point to the root of the repository
  tags = ['files', 'sorting']
}
```

With `publishPlugins` task you can publish your plugin to Gradle Plugin Portal. As describe in the tutorial for publishing 
for the first time you might wait a day or two that the plugin will be approved. In case your submission is not complete you 
will receive an email with detailed explanation of what additional steps are necessary.

## Published Version

Plugin is being published on Gradle Plugin Portal as [com.rivancic.files-plugin](https://plugins.gradle.org/plugin/com.rivancic.files-plugin)

## Resources

[Publishing Plugins to the Gradle Plugin Portal (Gradle Userguide)](https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html)

[How do I publish my plugin to the Plugin Portal? (Gradle Plugin Portal)](https://plugins.gradle.org/docs/publish-plugin)

<!--

### Examples

// TODO add some examples of real projects that are published to Gradle Plugin Portal. 
For example SpringBoot / Dependency management plugins. Add also the links to those plugins.

-->