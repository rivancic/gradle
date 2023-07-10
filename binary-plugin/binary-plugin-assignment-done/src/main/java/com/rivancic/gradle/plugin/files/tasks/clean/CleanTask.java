package com.rivancic.gradle.plugin.files.tasks.clean;

import org.gradle.api.tasks.Delete;

/**
 * Task that Removes sorted files. Deletes build directory.
 *
 * <ul>
 *   <li>
 *     It's grouped under {@link #FILES_GROUP_NAME}.
 *   </li>
 *   <li>
 *     It's description {@link #CLEAN_TASK_DESCRIPTION}.
 *   </li>
 * </ul>
 */
public class CleanTask extends Delete {

  public static final String FILES_GROUP_NAME = "files";
  public static final String CLEAN_TASK_DESCRIPTION = "Clean build directory";

  public CleanTask() {
    setGroup(FILES_GROUP_NAME);
    setDescription(CLEAN_TASK_DESCRIPTION);
    delete(getProject().getLayout().getBuildDirectory());
  }
}