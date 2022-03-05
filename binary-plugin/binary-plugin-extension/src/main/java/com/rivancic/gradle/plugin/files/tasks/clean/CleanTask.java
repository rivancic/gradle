package com.rivancic.gradle.plugin.files.tasks.clean;

import org.gradle.api.tasks.Delete;

public class CleanTask extends Delete {

  public static final String FILES_GROUP_NAME = "files";
  public static final String CLEAN_TASK_DESCRIPTION = "Clean build directory";

  public CleanTask() {
    setGroup(FILES_GROUP_NAME);
    setDescription(CLEAN_TASK_DESCRIPTION);
    delete(getProject().getLayout().getBuildDirectory());
  }
}