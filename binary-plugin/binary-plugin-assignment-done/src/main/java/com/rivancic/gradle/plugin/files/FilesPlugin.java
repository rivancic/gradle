package com.rivancic.gradle.plugin.files;

import com.rivancic.gradle.plugin.files.tasks.clean.CleanTask;
import com.rivancic.gradle.plugin.files.tasks.sort.SortFilesTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Implementation of Plugin Gradle API interface. In the {@link Plugin#apply(Object)} method we configure base plugin logic.
 *
 * In our case we first log lines so that we know plugin is being applied in the first place.
 *
 * Then we just register new tasks so that they are configured once they need to be invoked.
 *
 * We have to check first before adding {@link CleanTask} task as it might already be added to the project if you would apply "java" plugin to the main project.
 *
 * As the last part we register {@link SortFilesTask} task. Task for sorting files also depends on clean task so we always get expected result.
 */
public class FilesPlugin implements Plugin<Project> {

  private static final String CLEAN_TASK_NAME = "clean";
  private static final String SORT_FILES_TASK_NAME = "sortFiles";

  @Override
  public void apply(Project project) {

    project.getLogger().info("=============================================================================================");
    project.getLogger().info("                            Binary Files Plugin                                              ");
    project.getLogger().info("=============================================================================================");

    // Check if clean task is already registered. Then don't add it. As it can conflict with java clean task if java plugin is applied to the same project.
    if (project.getTasks().findByName(CLEAN_TASK_NAME) == null) {
      project.getTasks().register(CLEAN_TASK_NAME, CleanTask.class);
    }

    project.getTasks()
        .register(SORT_FILES_TASK_NAME, SortFilesTask.class)
        .configure(action -> action.dependsOn(project.getTasks().getByName(CLEAN_TASK_NAME)));
  }
}
