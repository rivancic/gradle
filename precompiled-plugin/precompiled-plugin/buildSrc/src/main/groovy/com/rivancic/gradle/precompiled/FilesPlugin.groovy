package com.rivancic.gradle.precompiled

import com.rivancic.gradle.precompiled.tasks.CleanTask
import com.rivancic.gradle.precompiled.tasks.SortFilesTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This build script contains two tasks. Main functionality is sorting files by their extension.
 *
 * Tasks are grouped into "files" group.
 *
 * Root folder containing files that have to be sorted is parametrized through Gradle properties with the key [tasks.files.folder].
 * Property has to be accessed through the project.ext map as direct reference doesn't work if property key is named with the dot notation.
 *
 * Second task clears build folder.
 */
class FilesPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {

    project.tasks.register('sortFiles', SortFilesTask.class, {
      inputDir = project.file(project.properties.get("tasks.files.folder"))
      outputDir = project.layout.buildDirectory.dir('files').get().asFile
    })

    project.tasks.register('clean', CleanTask.class, {
      inputDir = project.layout.buildDirectory.asFile.get().exists() ? project.layout.buildDirectory : null
    })
  }
}
