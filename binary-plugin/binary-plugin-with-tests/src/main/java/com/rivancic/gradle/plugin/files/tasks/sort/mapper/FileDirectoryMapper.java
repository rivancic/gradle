package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import java.io.File;
import java.io.IOException;

/**
 * Implements mapping a file to specific directory name. The logic is base for the sorting algorithm.
 */
public interface FileDirectoryMapper {

  /**
   * Returns directory name based on the file attributes.
   *
   * @param file for which the calculated directory has to be returned.
   * @return relative path name of the directory into which the file will be sorted.
   * @throws IOException in case provided file can't be read.
   */
  String getDirectory(File file) throws IOException;
}