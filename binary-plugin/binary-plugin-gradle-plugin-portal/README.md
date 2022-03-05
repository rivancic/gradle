# Binary Plugin Gradle Plugin Portal

Publishing binary Gradle plugin to Gradle Plugin Portal.

[Gradle Plugin Portal](https://plugins.gradle.org/), a centralized, searchable repository dedicated to Gradle plugins.

In order to prepare the project for publishing it to Gradle Plugin Portal you have to make sure you add following configuration:
1) Save Gradle Plugin Portal credentials to ~/.gradle/gradle.properties
   
```properties
gradle.publish.key=...
gradle.publish.secret=...
```
2) In project apply **com.gradle.plugin-publish** plugin (TODO add link here).
   
```groovy
plugins {
  id "java-gradle-plugin"
  id "maven-publish"
  id 'com.gradle.plugin-publish' version '0.20.0'
}
```
3) Verify that you have `description` set in `gradlePlugin{}` (TODO link here) extension block.
   
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
4) In `pluginBundle{}` (TODO link here) extension you have to add required metadata `website`, `vcsUrl` and `tags` properties for the plugin.

```groovy
pluginBundle {
  website = 'https://github.com/rivancic/gradle'
  vcsUrl = 'https://github.com/rivancic/gradle/tree/master/binary-plugin'
  tags = ['files', 'sorting']
}
```

With `publishPlugins` task you can publish your plugin to Gradle Plugin Portal. As describe in the tutorial for publishing 
for the first time you might wait a day or two that the plugin will be approved.

TODO Add here code parts and explain how to add credentials to ~/.gradle directory

## Resources

[Publishing Plugins to the Gradle Plugin Portal (Gradle Userguide)](https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html)

[How do I publish my plugin to the Plugin Portal? (Gradle Plugin Portal)](https://plugins.gradle.org/docs/publish-plugin)