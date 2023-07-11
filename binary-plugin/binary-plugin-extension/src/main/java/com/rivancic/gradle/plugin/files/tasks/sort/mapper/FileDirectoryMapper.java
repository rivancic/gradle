package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import java.io.File;
import java.io.IOException;

/**
 * Implements mapping a file to specific directory name. The logic is based on the sorting algorithm.
 */
public interface FileDirectoryMapper {

  /**
   * Function that has to, based on the file name return name of a directory.
   * It maps file name to directory name where the file will be sorted.
   *
   * @param file from which file name will be taken.
   * @return computed directory name.
   * @throws IOException in case the file can't be read/accessed.
   */
  String getDirectory(File file) throws IOException;
}