package com.rivancic.gradle.plugin.files.tasks.sort;

import org.gradle.api.provider.Property;

public interface SortFilesTaskExtension {

  public Property<String> getSortType();
  public Property<String> getDirectoryLocation();

/*
  public Property<String> getSortType() {
    return sortType;
  }

  public void setSortType(Property<String> sortType) {
    this.sortType = sortType;
  }

  public Property<String> getDirectoryLocation() {
    return directoryLocation;
  }

  public void setDirectoryLocation(Property<String> directoryLocation) {
    this.directoryLocation = directoryLocation;
  }*/
}
