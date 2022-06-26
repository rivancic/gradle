package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import java.io.File;

/**
 * Maps file extension to directory name.
 */
public class FileDirectoryExtensionMapper implements FileDirectoryMapper {

  @Override
  public String getDirectory(File file) {
    return getExtensionOf(file.getName());
  }

  /**
   * Get file extension from its file name.
   *
   * If filename is image.jpg then the method will return "jpg".
   *
   * @param filename from which extension will be extracted
   * @return extension of the file
   */
  static String getExtensionOf(String filename) {
    return filename.substring(filename.lastIndexOf(".") + 1);
  }
}
