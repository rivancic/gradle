package com.rivancic.gradle.plugin.files.tasks.sort;

import org.gradle.api.provider.Property;

public interface SortFilesTaskExtension {

  Property<String> getSortType();

  Property<String> getDirectoryLocation();
}
