package com.rivancic.gradle.precompiled.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity

/**
 * TODO show all the obstacles to make a task cachable
 *
 * Annotate with CacheableTask
 *
 * @InputDirectory -> has to have normalization strategy https://docs.gradle.org/7.0/userguide/validation_problems.html#missing_normalization_annotation
 */
@CacheableTask
abstract class FilesTask extends DefaultTask {
  @PathSensitive(PathSensitivity.RELATIVE)
  @InputDirectory
  abstract DirectoryProperty getInputDir()

  @OutputDirectory
  abstract DirectoryProperty getOutputDir()

  FilesTask() {
    group = "files"
  }

  /**
   * Get file extension from its file name
   *
   * @param filename from which extension will be extracted
   * @return extension of the file
   */
  static String getExtensionOf(String filename) {
    return filename.substring(filename.lastIndexOf(".") + 1)
  }
}