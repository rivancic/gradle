package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Project;

/**
 * Class containing static factory method {@link #getFileDirectoryMapper(Project)}} that will return concrete
 * {@link FileDirectoryMapper} instance based on the sorting type.
 */
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
      return switch (sortType) {
        case "extension" -> new FileDirectoryExtensionMapper();
        case "date" -> new FileDirectoryDateMapper();
        case "alphabet" -> new FileDirectoryAlphabetMapper();
        default ->
            throw new InvalidUserDataException("Invalid property tasks.files.sortType value provided [" + sortType + "]. Valid values are ['extension','date','alphabet']");
      };
    } else {
      project.getLogger().quiet("Property [tasks.files.sortType] isn't set, default sorting will be done by creation date");
      return new FileDirectoryDateMapper();
    }
  }
}
