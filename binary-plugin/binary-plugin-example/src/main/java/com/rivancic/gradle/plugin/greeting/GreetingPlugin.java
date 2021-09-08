package com.rivancic.gradle.plugin.greeting;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Simple Plugin that will print to Gradle logger that Plugin has been applied at the configuration phase when
 * apply method will be executed.
 */
public class GreetingPlugin implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    project.getLogger().quiet("======================================");
    project.getLogger().quiet("Applying Gradle Binary Greeting Plugin");
    project.getLogger().quiet("======================================");
  }
}
