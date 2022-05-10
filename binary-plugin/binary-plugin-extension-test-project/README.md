# Binary Plugin Extension Test Project

This is a helper project to test [Binary Plugin Extension](../binary-plugin-extension) project.

Binary plugin with its `sortFiles` extension is applied to the project in [build.gradle](build.gradle) file.

```groovy
// Apply com.rivancic.files-plugin Gradle plugin
plugins {
  id "com.rivancic.files-plugin" version "${filesPluginVersion}"
}

// Define plugin configuration through its extension
sortFiles {
  sortType = getProject().getExtensions().getExtraProperties().get('tasks.files.sortType')
  directoryLocation = getProject().getExtensions().getExtraProperties().get('tasks.files.folder')
}
```

`filesPluginVersion` is specified in [gradle.properties](gradle.properties) file.

Files from `files` directory are sorted into [build/files](build/files) directory.