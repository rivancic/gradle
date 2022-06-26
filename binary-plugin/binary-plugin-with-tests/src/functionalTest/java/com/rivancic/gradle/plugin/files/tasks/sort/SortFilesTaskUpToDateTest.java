package com.rivancic.gradle.plugin.files.tasks.sort;

import com.rivancic.gradle.plugin.files.tasks.FileTestHelper;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.gradle.testkit.runner.TaskOutcome.*;

/**
 * Test that makes sure <b>sortFiles</b> task is implemented correctly and will be skipped if executed for the second time.
 * When the task is executed with same inputs the task outcome should be UP_TO_DATE.
 */
public class SortFilesTaskUpToDateTest {

  @TempDir
  File testProjectDir;
  FileTestHelper fileTestHelper;

  @BeforeEach
  public void setup() {
    fileTestHelper = new FileTestHelper(testProjectDir);
  }

  @Test
  public void whenSortingTwice_thenUpToDateState() throws IOException {
    createSettingsFile();
    createBuildFile(BUILD_FILE_EXTENSION_SORT_BY_ALPHABET);
    createFilesToSort(Arrays.asList("image.jpg", "document.doc", "executable.exe"));

    GradleRunner testGradleRunner = GradleRunner.create()
        .withProjectDir(testProjectDir)
        .withArguments("sortFiles")
        .withPluginClasspath();

    BuildResult result = testGradleRunner.build();

    System.out.println("Plugin output:");
    System.out.println(result.getOutput());

    printDirectoryContent();

    Assertions.assertEquals(SUCCESS, result.task(":sortFiles").getOutcome());

    // Execute same task for the second time
    BuildResult repeatedResult = testGradleRunner.build();

    Assertions.assertEquals(UP_TO_DATE, repeatedResult.task(":sortFiles").getOutcome());
  }

  private final static String BUILD_FILE_EXTENSION_SORT_BY_ALPHABET = """
        plugins {
          id "com.rivancic.files-plugin"
        };
        
        sortFiles {
          sortType = "alphabet"
        }
        """;

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
    System.out.println("");

    System.out.println("Build/files:");
    fileTestHelper.printAllFilesIn("build/files");
    System.out.println("=======================");
  }

  private void createFilesToSort(List<String> files) throws IOException {
    fileTestHelper.createDirectory("files");
    for (String file : files) {
      fileTestHelper.createNewFileWithContent("files/" + file, "test");
    }
  }

  private void createBuildFile(String buildFileContent) throws IOException {
    fileTestHelper.createNewFileWithContent("build.gradle", buildFileContent);
  }

  private void createSettingsFile() throws IOException {
    fileTestHelper.createNewFileWithContent("settings.gradle", "rootProject.name = 'filesPlugin'");
  }
}
