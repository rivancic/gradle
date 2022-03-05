package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.provider.Provider;

public class FileDirectoryMapperFactory {

  /**
   * Fetches sorting type from [tasks.files.sortType] properties. If property is not present then files will be sorted by the date.
   *
   * TODO improve comments
   *
   * @param project holding properties.
   * @return FileDirectoryMapper that actually defines sorting algorithm
   */
  public static FileDirectoryMapper getFileDirectoryMapper(Logger logger, Provider<String> sortType) {
    //String sortType;
    if (sortType.isPresent()) {
      //sortType = sortType.get();
      if (sortType.get().equals("extension")) {
        return new FileDirectoryExtensionMapper();
      } else if (sortType.get().equals("date")) {
        return new FileDirectoryDateMapper();
      } else if (sortType.get().equals("alphabet")) {
        return new FileDirectoryAlphabetMapper();
      } else {
        throw new InvalidUserDataException("Invalid property tasks.files.sortType value provided [" + sortType + "]. Valid values are ['extension','date']");
      }
    } else {
      logger.quiet("Property [tasks.files.sortType] isn't set, default sorting will be done by creation date");
      return new FileDirectoryDateMapper();
    }
  }
}
