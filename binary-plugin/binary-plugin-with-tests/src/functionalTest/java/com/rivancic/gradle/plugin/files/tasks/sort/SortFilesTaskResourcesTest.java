package com.rivancic.gradle.plugin.files.tasks.sort;

import com.rivancic.gradle.plugin.files.tasks.FileTestHelper;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;

/**
 * Test that uses files in resources folder that define the project instead of programmatically creating them.
 * Can make test code more compact.
 */
public class SortFilesTaskResourcesTest {

  File testProjectDir = new File("src/functionalTest/resources/testProject1");
  FileTestHelper fileTestHelper;

  @BeforeEach
  public void setup() {
    fileTestHelper = new FileTestHelper(testProjectDir);
  }

  @AfterEach
  public void cleanUp() throws IOException {
    fileTestHelper.removeDirectory("build");
  }

  @Test
  public void whenSortingTwice_thenUpToDateState() {
    GradleRunner testGradleRunner = GradleRunner.create()
        .withProjectDir(testProjectDir)
        .withArguments("sortFiles")
        .withPluginClasspath();

    BuildResult result = testGradleRunner.build();

    System.out.println("Plugin output:");
    System.out.println(result.getOutput());

    printDirectoryContent();

    Assertions.assertEquals(SUCCESS, result.task(":sortFiles").getOutcome());
    List<String> expectedFilePaths = Arrays.asList("build/files/b/business.jpg", "build/files/s/scanner.wav", "build/files/s/stock.jpg");
    expectedFilePaths.forEach(expectedFilePath -> {
      Assertions.assertTrue(new File(testProjectDir, expectedFilePath).exists());
    });
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
    System.out.println("");

    System.out.println("Build/files:");
    fileTestHelper.printAllFilesIn("build/files");
    System.out.println("=======================");
  }
}
