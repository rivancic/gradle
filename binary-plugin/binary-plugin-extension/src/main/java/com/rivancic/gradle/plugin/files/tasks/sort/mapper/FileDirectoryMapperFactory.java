package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import org.gradle.api.InvalidUserDataException;
import org.gradle.api.logging.Logger;
import org.gradle.api.provider.Provider;

public class FileDirectoryMapperFactory {

  private FileDirectoryMapperFactory(){}

  /**
   * Factory method that based on provided sorting type parameter returns concrete FileDirectoryMapper.
   * If property isn't present then files will be sorted by the date ({@link FileDirectoryDateMapper}).
   *
   * @param logger for logging warning about misconfigured plugin.
   * @param sortType based on which FileDirectoryMapper will be chosen.
   * @return FileDirectoryMapper that actually defines sorting algorithm
   */
  public static FileDirectoryMapper getFileDirectoryMapper(Logger logger, Provider<String> sortType) {
    if (sortType.isPresent()) {
      if (sortType.get().equals("extension")) {
        return new FileDirectoryExtensionMapper();
      } else if (sortType.get().equals("date")) {
        return new FileDirectoryDateMapper();
      } else if (sortType.get().equals("alphabet")) {
        return new FileDirectoryAlphabetMapper();
      } else {
        throw new InvalidUserDataException("Invalid property sortType value provided [" + sortType + "]. Valid values are ['extension','date','alphabet']");
      }
    } else {
      logger.quiet("Property sortType isn't set, default sorting will be done by creation date");
      return new FileDirectoryDateMapper();
    }
  }
}
