package com.rivancic.gradle.plugin.files.tasks.sort;

import org.gradle.api.provider.Property;

/**
 * Extension that will be used in plugin for sorting files task configuration.
 */
public interface SortFilesTaskExtension {

  /**
   * @return Property that holds sorting type.
   */
  Property<String> getSortType();

  /**
   * @return Property that holds location to the directory containing files to be sorted.
   */
  Property<String> getDirectoryLocation();
}
