package com.rivancic.gradle.plugin.files.tasks.clean;

import com.rivancic.gradle.plugin.files.tasks.FileTestHelper;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;

/**
 * <p>
 * Functional test of <b>clean</b> task with TestKit.
 *</p>
 * Test that <b>clean</b> task removes build directory.
 */
public class CleanTaskTest {

  @TempDir
  File testProjectDir;
  FileTestHelper fileTestHelper;

  @BeforeEach
  void initiateFileTestHelper() {
    fileTestHelper = new FileTestHelper(testProjectDir);
  }

  @Test
  public void cleanTaskTest() throws IOException {
    createSettingsFile();
    createBuildFile();
    createFilesToSort();
    createSortedFilesInBuildDirectory();

    BuildResult result = GradleRunner.create()
        .withProjectDir(testProjectDir)
        .withArguments("clean")
        .withPluginClasspath()
        .build();

    System.out.println("Plugin output:");
    System.out.println(result.getOutput());
    printDirectoryContent();

    Assertions.assertEquals(SUCCESS, result.task(":clean").getOutcome());
    Assertions.assertTrue(new File(testProjectDir, "files/image.jpg").exists());
    Assertions.assertFalse(new File(testProjectDir, "build/files/test.jpg").exists());
  }

  private void printDirectoryContent() {
    System.out.println("==== Print Test ========");
    System.out.println("Absolute path:");
    System.out.println(testProjectDir.getAbsolutePath());
    System.out.println("");

    System.out.println("Root:");
    fileTestHelper.printAllFilesIn("");
    System.out.println("");

    System.out.println("Root/files:");
    fileTestHelper.printAllFilesIn("files");
    System.out.println("=======================");
  }

  private void createSortedFilesInBuildDirectory() throws IOException {
    fileTestHelper.createDirectory("build/files");
    fileTestHelper.createNewFileWithContent("build/files/build.gradle", "test");
  }

  private void createFilesToSort() throws IOException {
    fileTestHelper.createDirectory("files");
    fileTestHelper.createNewFileWithContent("files/image.jpg", "test");
  }

  private void createBuildFile() throws IOException {
    String buildFileContent = """
        plugins {
          id "com.rivancic.files-plugin"
        };
        """;
    fileTestHelper.createNewFileWithContent("build.gradle", buildFileContent);
  }

  private void createSettingsFile() throws IOException {
    fileTestHelper.createNewFileWithContent("settings.gradle", "rootProject.name = 'filesPlugin'");
  }
}
