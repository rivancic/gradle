package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Project;

public class FileDirectoryMapperFactory {

  /**
   * Fetches sorting type from [tasks.files.sortType] properties. If property is not present then files will be sorted by the date.
   *
   * @param project holding properties.
   * @return FileDirectoryMapper that actually defines sorting algorithm
   */
  public static FileDirectoryMapper getFileDirectoryMapper(Project project) {
    String sortType;
    if (project.hasProperty("tasks.files.sortType")) {
      sortType = project.getExtensions().getExtraProperties().get("tasks.files.sortType").toString();
      if (sortType.equals("extension")) {
        return new FileDirectoryExtensionMapper();
      } else if (sortType.equals("date")) {
        return new FileDirectoryDateMapper();
      } else if (sortType.equals("alphabet")) {
        return new FileDirectoryAlphabetMapper();
      } else {
        throw new InvalidUserDataException("Invalid property tasks.files.sortType value provided [" + sortType + "]. Valid values are ['extension','date','alphabet']");
      }
    } else {
      project.getLogger().quiet("Property [tasks.files.sortType] isn't set, default sorting will be done by creation date");
      return new FileDirectoryDateMapper();
    }
  }
}
