package com.rivancic.gradle.precompiled.tasks

import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.StandardCopyOption

/**
 * This method sorts files inside specific directory.
 *
 * <ul>
 *   <li>
 *     Files to be sorted are taken from FilesTask#inputDir
 *   </li>
 *   <li>
 *     Result is being stored in FilesTask#outputDir
 *   </li>
 * <ul>
 *
 * This task can be configuration cached as its not referring to the Project instance.
 *
 * Only none hidden files are taken into consideration.
 *
 * For every extension new subdirectory is created and file with that extension is moved to matching subdirectory.
 */
@CacheableTask
abstract class SortFilesTask extends FilesTask {

  SortFilesTask() {
    description = "Sorts files in given directory into subdirectories based on their file extensions"
  }

  @TaskAction
  def sort() {
    logger.info "==== Sorting Files ===="
    logger.info ""

    inputDir.get().asFile
        .listFiles({ file -> file.isFile() && !file.isHidden() } as FileFilter)
        .each {

          // Extract name and extension of the file
          def fileName = it.getName()
          def extension = getExtensionOf fileName

          // Create dir of that extension
          Files.createDirectories(outputDir.get().asFile.toPath().resolve(extension))

          // Move file to that directory
          Files.copy(
              it.toPath(),
              outputDir.get().asFile.toPath().resolve("$extension/$fileName"),
              StandardCopyOption.REPLACE_EXISTING)
        }

    logger.info ""
    logger.info "======================="
  }
}