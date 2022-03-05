package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import java.io.File;

/**
 * Maps file initial to alphabetical ordered directory
 */
public class FileDirectoryAlphabetMapper implements FileDirectoryMapper {

  @Override
  public String getDirectory(File file) {
    return file.getName().substring(0,1);
  }
}
