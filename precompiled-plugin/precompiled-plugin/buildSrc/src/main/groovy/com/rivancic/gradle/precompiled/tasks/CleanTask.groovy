package com.rivancic.gradle.precompiled.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Optional

import java.nio.file.Files
import java.nio.file.Path

/**
 *
 */
@CacheableTask
abstract class CleanTask extends DefaultTask {

  @PathSensitive(PathSensitivity.RELATIVE)
  @InputDirectory
  @Optional
  abstract DirectoryProperty getInputDir()

  CleanTask() {
    group = "files"
    description = "Deletes build folder"
  }

  @TaskAction
  def sort() {
    logger.info "==== Clean build ===="
    logger.info ""

    def filestodel = inputDir.getOrNull();
    if (filestodel != null) {
      try (def stream = Files.walk(filestodel.asFile.toPath())) {
        stream.sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .peek(file -> logger.info("File to del ${file.toString()}"))
            .map(File::delete).count()
      }
    }

    logger.info ""
    logger.info "======================="
  }
}