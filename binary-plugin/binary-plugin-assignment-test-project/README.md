# Binary Plugin Test Project

This is a helper project to test [Binary Plugin Assignment](../binary-plugin-assignment-done).

Binary plugin is applied to the project in [build.gradle](build.gradle) file

```groovy
plugins {
  id "com.rivancic.files-plugin" version "${filesPluginVersion}"
}
```

`filesPluginVersion` is secified in [gradle.properties](gradle.properties) file.

Files form `files` directory are sorted into [build/files](build/files) directory.