package com.rivancic.gradle.plugin.files.tasks.clean;

import org.gradle.api.tasks.Delete;

/**
 * Helper {@link org.gradle.api.Task} that will clean build directory.
 */
public class CleanTask extends Delete {

  /**
   * Default Task group.
   */
  public static final String FILES_GROUP_NAME = "files";

  /**
   * User friendly task description
   */
  public static final String CLEAN_TASK_DESCRIPTION = "Clean build directory";

  /**
   * Configures group name and description.
   * Lastly it configures build directory to be deleted when the task executes.
   */
  public CleanTask() {
    setGroup(FILES_GROUP_NAME);
    setDescription(CLEAN_TASK_DESCRIPTION);
    delete(getProject().getLayout().getBuildDirectory());
  }
}