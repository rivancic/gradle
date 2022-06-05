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

import static org.gradle.testkit.runner.TaskOutcome.FAILED;

/**
 * Test that <b>sortFiles</b> task fails if input directory is missing.
 */
public class SortFilesMissingInputTest {

  @TempDir
  File testProjectDir;
  FileTestHelper fileTestHelper;

  @BeforeEach
  public void setup() {
    fileTestHelper = new FileTestHelper(testProjectDir);
  }

  @Test
  public void whenBuildingWithMissingOriginDirectory_thenFail() throws IOException {
    createSettingsFile();
    createBuildFile(BUILD_FILE_EXTENSION_SORT_BY_ALPHABET);

    BuildResult result = GradleRunner.create()
        .withProjectDir(testProjectDir)
        .withArguments("sortFiles")
        .withPluginClasspath()
        .buildAndFail();

    System.out.println("Plugin output:");
    System.out.println(result.getOutput());

    printDirectoryContent();

    Assertions.assertEquals(FAILED, result.task(":sortFiles").getOutcome());
    Assertions.assertTrue(
        result.getOutput().contains("Reason: An input file was expected to be present but it doesn't exist."));
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

  private void createBuildFile(String buildFileContent) throws IOException {
    fileTestHelper.createNewFileWithContent("build.gradle", buildFileContent);
  }

  private void createSettingsFile() throws IOException {
    fileTestHelper.createNewFileWithContent("settings.gradle", "rootProject.name = 'filesPlugin'");
  }
}
