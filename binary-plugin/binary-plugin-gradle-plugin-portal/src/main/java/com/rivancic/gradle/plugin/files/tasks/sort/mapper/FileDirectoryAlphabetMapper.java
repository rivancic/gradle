package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import java.io.File;

/**
 * <p>Maps file initial to alphabetical ordered directory.</p>
 *
 * <p>Example:</p>
 *
 * <pre>
 * business.jpg -> b
 * scanner.wav -> s
 * stock.jpg -> s
 * </pre>
 */
public class FileDirectoryAlphabetMapper implements FileDirectoryMapper {

  @Override
  public String getDirectory(File file) {
    return file.getName().substring(0,1);
  }
}
