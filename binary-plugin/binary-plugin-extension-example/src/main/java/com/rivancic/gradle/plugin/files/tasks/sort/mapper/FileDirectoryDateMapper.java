package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Maps file creation date to directory name.
 */
public class FileDirectoryDateMapper implements FileDirectoryMapper {
  @Override
  public String getDirectory(File file) throws IOException {
    FileTime creationTime = (FileTime) Files.getAttribute(Paths.get(file.getPath()), "creationTime");
    DateTimeFormatter fileCreationDateFormat = DateTimeFormatter.ofPattern("MM-YYYY");
    return fileCreationDateFormat.format(Instant.ofEpochMilli(creationTime.toMillis()).atZone(ZoneOffset.UTC).toLocalDate());
  }
}
