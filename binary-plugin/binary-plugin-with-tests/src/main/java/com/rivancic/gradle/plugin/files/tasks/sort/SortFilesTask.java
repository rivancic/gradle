package com.rivancic.gradle.plugin.files.tasks.sort;

import com.rivancic.gradle.plugin.files.tasks.sort.mapper.FileDirectoryMapper;
import com.rivancic.gradle.plugin.files.tasks.sort.mapper.FileDirectoryMapperFactory;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

/**
 * Task is grouped into "files" group.
 * <p>
 * Root folder containing files that have to be sorted is parametrized through {@link SortFilesTaskExtension#getDirectoryLocation()}.
 * This plugin sorts files from specific directory and copies them to <code>build/files</code> directory.
 * Files are being sorted based on their extension, creation date or initial character. Default sorting type is by creation date.
 * One can explicitly define sorting type with setting {@link SortFilesTaskExtension#getSortType()} to value:
 * <ul>
 *     <li>date</li>
 *     <li>extension</li>
 *     <li>alphabeth</li>
 * </ul>
 * <p>
 * Files are being resolved with the Project#file() method, as this way we get correct path to the relative path.
 * Only none hidden files are taken into consideration.
 * <p>
 * Based on sorting type new subdirectory is created in build target and file is copied to matching subdirectory.
 * Name of the subdirectory is defined with {@link FileDirectoryMapper} instance.
 */
public abstract class SortFilesTask extends DefaultTask {

  @Input
  public abstract Property<String> getSortType();

  /**
   * With correctly defined task inputs and outputs task can be skipped during the execution.
   * See https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:up_to_date_checks
   */
  @InputDirectory
  public abstract DirectoryProperty getDirectoryLocation();

  @OutputDirectory
  public abstract DirectoryProperty getOutputDir();

  public SortFilesTask() {
    setGroup("files");
    setDescription("Sorts files in given directory into build/files subdirectories based on the sorting type [date, extension, alphabet]");
  }

  // Action that will be executed
  @TaskAction
  public void apply() {

    getProject().getLogger().quiet("==== Sorting Files ====");

    // Deleting of old files can't be extracted to another task, its atomic part of the sorting task itself.
    // https://discuss.gradle.org/t/run-task-if-other-is-not-up-to-date/25340
    getProject().delete(getProject().getLayout().getBuildDirectory().dir("files"));

    FileDirectoryMapper fileMapper = FileDirectoryMapperFactory.getFileDirectoryMapper(getProject().getLogger(), getSortType());

    if (getDirectoryLocation().isPresent()) {
      Arrays.stream(getDirectoryLocation().get().getAsFile()
          .listFiles())
          .filter(file -> file.isFile() && !file.isHidden())
          .forEach(file -> {
            getProject().getLogger().quiet("Filename: " + file.getName());
            try {
              String directory = fileMapper.getDirectory(file);
              getProject().mkdir(getProject().getLayout().getBuildDirectory().dir("files/" + directory + "/"));
              Files.copy(file.toPath(), getOutputDir().get().dir("" + directory + "/" + file.getName()).getAsFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
              getProject().getLogger().warn("Couldn't sort " + file.getName() + " file properly." + e);
            }
          });
    } else {
      getProject().getLogger().info("Please provide as input a file that contains files to sort");
    }

    getProject().getLogger().quiet("");
    getProject().getLogger().quiet("=======================");
  }
}
