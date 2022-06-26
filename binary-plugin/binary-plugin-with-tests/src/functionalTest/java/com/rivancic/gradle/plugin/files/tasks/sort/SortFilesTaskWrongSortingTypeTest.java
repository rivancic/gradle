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

import static org.gradle.testkit.runner.TaskOutcome.FAILED;

/**
 * Test that verifies how plugin behaves when wrong <b>sortType</b> is defined in the extension.
 */
public class SortFilesTaskWrongSortingTypeTest {

  @TempDir
  File testProjectDir;
  FileTestHelper fileTestHelper;

  @BeforeEach
  public void setup() {
    fileTestHelper = new FileTestHelper(testProjectDir);
  }

  @Test
  public void whenBuildingWithWrongSortType_thenFail() throws IOException {
    createSettingsFile();
    createBuildFile(BUILD_FILE_EXTENSION_SORT_BY_WRONG_TYPE);
    createFilesToSort(Arrays.asList("image.jpg", "document.doc", "executable.exe"));

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
        result.getOutput().contains("Invalid property sortType value provided [wrong]. Valid values are ['extension','date','alphabet']"));
  }

  private final static String BUILD_FILE_EXTENSION_SORT_BY_WRONG_TYPE = """
        plugins {
          id "com.rivancic.files-plugin"
        };
        
        sortFiles {
          sortType = "wrong"
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
