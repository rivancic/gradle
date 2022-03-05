package com.rivancic.gradle.plugin.files.tasks.sort;

import com.rivancic.gradle.plugin.files.tasks.sort.mapper.FileDirectoryMapper;
import com.rivancic.gradle.plugin.files.tasks.sort.mapper.FileDirectoryMapperFactory;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

/**
 * Task is grouped into "files" group.
 *
 * Root folder containing files that have to be sorted is parametrized through Gradle properties with the key [tasks.files.folder].
 * Property has to be accessed through the project.ext map as direct reference doesn't work if property key is named with the dot notation.
 *
 * This method sorts files from specific directory and copies them to build/files directory.
 * Files are being sorted based on their extension, creation date or initial character. Default sorting type is by creation date.
 * One can explicitly define sorting type with setting following property key [tasks.files.sortType] to value:
 * <ul>
 *     <li>date</li>
 *     <li>extension</li>
 *     <li>alphabeth</li>
 * </ul>
 *
 * Files are being resolved with the Project#file() method, as this way we get correct path to the relative path.
 * Only none hidden files are taken into consideration.
 *
 * Based on sorting type new subdirectory is created in build target and file is copied to matching subdirectory.
 * Name of the subdirectory is defined with FileDirectoryMapper.
 */
public class SortFilesTask extends DefaultTask {

  public SortFilesTask() {
    setGroup("files");
    setDescription("Sorts files in given directory into build/files subdirectories based on the sorting type [date, extension, alphabet]");
  }

  // action that will be executed
  @TaskAction
  public void apply() {

    getProject().getLogger().quiet("==== Sorting Files ====");

    FileDirectoryMapper fileMapper = FileDirectoryMapperFactory.getFileDirectoryMapper(getProject());

    Arrays.stream(getProject().file(getProject().getExtensions().getExtraProperties().get("tasks.files.folder"))
        .listFiles())
        .filter(file -> file.isFile() && !file.isHidden())
        .forEach(file -> {
          getProject().getLogger().quiet("Filename: " + file.getName());
          try {
            String directory = fileMapper.getDirectory(file);
            getProject().mkdir(getProject().getLayout().getBuildDirectory().dir("files/" + directory + "/"));
            Files.copy(file.toPath(), getProject().getLayout().getBuildDirectory().dir("files/" + directory + "/" + file.getName()).get().getAsFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
          } catch (IOException e) {
            getProject().getLogger().warn("Couldn't sort " + file.getName() + " file properly.");
          }
        });

    getProject().getLogger().quiet("");
    getProject().getLogger().quiet("=======================");
  }
}
