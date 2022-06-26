package com.rivancic.gradle.plugin.files.tasks.sort;

import org.gradle.api.provider.Property;

/**
 * Interface representing plugin extension. Note {@link Property} properties that will be set at execution time.
 */
public interface SortFilesTaskExtension {

  /**
   * @return property holding sorting type value.
   */
  Property<String> getSortType();

  /**
   * @return property holding original directory relative location.
   */
  Property<String> getDirectoryLocation();
}
