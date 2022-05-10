package com.rivancic.gradle.plugin.files;

import com.rivancic.gradle.plugin.files.tasks.clean.CleanTask;
import com.rivancic.gradle.plugin.files.tasks.sort.SortFilesTask;
import com.rivancic.gradle.plugin.files.tasks.sort.SortFilesTaskExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Implementation of Plugin Gradle API interface. In the {@link Plugin#apply(Object)} method we configure base plugin logic.
 * <p>
 * First its logged to the console so that we know plugin is being applied.
 * <p>
 * Next extension for parametrizing tasks is registered. Example of the extension:
 * <pre>
 * sortFilesExtension {
 *   sortType = ...
 *   directoryLocation = ...
 * }
 * </pre>
 *<p>
 * Then we register new tasks so that they are configured once they need to be invoked.
 *<p>
 * We have to check first before adding {@link CleanTask} task as it might already be added to the project if you would apply "java" plugin to the main project.
 *<p>
 * As the last part we register {@link SortFilesTask} task.
 */
public class FilesPlugin implements Plugin<Project> {

  private static final String CLEAN_TASK_NAME = "clean";
  private static final String SORT_FILES_TASK_NAME = "sortFiles";
  private static final String SORT_FILES_EXTENSION_NAME = "sortFiles";
  private static final String SORT_FILES_SOURCE_FOLDER = "files";
  private static final String SORT_FILES_TARGET_FOLDER = "files";
  private static final String DEFAULT_SORTING_TYPE = "date";

  @Override
  public void apply(Project project) {

    project.getLogger().info("=============================================================================================");
    project.getLogger().info("                            Binary Files Plugin                                              ");
    project.getLogger().info("=============================================================================================");

    SortFilesTaskExtension sortFilesTaskExtension = project.getExtensions().create(SORT_FILES_EXTENSION_NAME, SortFilesTaskExtension.class);

    // Check if clean task is already registered. Then don't add it. As it can conflict with java clean task if java plugin is applied to the same project.
    if (project.getTasks().findByName(CLEAN_TASK_NAME) == null) {
      project.getTasks().register(CLEAN_TASK_NAME, CleanTask.class);
    }

    project.getTasks()
        .register(SORT_FILES_TASK_NAME, SortFilesTask.class, task -> {
          task.getDirectoryLocation().set(
              project.file(
                  sortFilesTaskExtension.getDirectoryLocation().isPresent()?
                      sortFilesTaskExtension.getDirectoryLocation().get():
                      SORT_FILES_SOURCE_FOLDER
              )
          );
          task.getSortType().set(
              sortFilesTaskExtension.getSortType().isPresent()?sortFilesTaskExtension.getSortType().get():DEFAULT_SORTING_TYPE
          );
          task.getOutputDir().set(project.file(project.getLayout().getBuildDirectory().dir(SORT_FILES_TARGET_FOLDER)));
        });
  }
}
