package com.rivancic.gradle.plugin.files.tasks.sort;

import com.rivancic.gradle.plugin.files.tasks.FileTestHelper;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;

/**
 * Test that <b>sortFiles</b> task sorts files from the original source to <b>build/files</b> folder. It's parametrized, will test multiple different configurations.
 */
public class SortFilesTaskTest {

  @TempDir
  File testProjectDir;
  FileTestHelper fileTestHelper;

  @BeforeEach
  public void setup() {
    fileTestHelper = new FileTestHelper(testProjectDir);
  }

  @ParameterizedTest
  @MethodSource("provideTestArguments")
  public void whenProjectConfigured_thenExecuteSortFilesTaskSuccessfully(String buildFileContent, List<String> files, List<String> expectedFilePaths) throws IOException {
    createSettingsFile();
    createBuildFile(buildFileContent);
    createFilesToSort(files);

    BuildResult result = GradleRunner.create()
        .withProjectDir(testProjectDir)
        .withArguments("sortFiles")
        .withPluginClasspath()
        .build();

    System.out.println("Plugin output:");
    System.out.println(result.getOutput());

    printDirectoryContent();

    Assertions.assertEquals(SUCCESS, result.task(":sortFiles").getOutcome());
    expectedFilePaths.forEach(expectedFilePath -> {
      Assertions.assertTrue(new File(testProjectDir, expectedFilePath).exists());
    });
  }

  private static Stream<Arguments> provideTestArguments() {

    String currentMonth = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-yyyy"));

    return Stream.of(
        Arguments.of(BASE_BUILD_FILE_CONTENT,
            Arrays.asList("image.jpg", "document.doc", "executable.exe"),
            Arrays.asList("build/files/" + currentMonth + "/image.jpg", "build/files/" + currentMonth + "/document.doc", "build/files/" + currentMonth + "/executable.exe")
        ),
        Arguments.of(BUILD_FILE_EXTENSION_SORT_BY_ALPHABET,
            Arrays.asList("image.jpg", "document.doc", "executable.exe"),
            Arrays.asList("build/files/i/image.jpg", "build/files/d/document.doc", "build/files/e/executable.exe")
        ),
        Arguments.of(BUILD_FILE_EXTENSION_SORT_BY_EXTENSION,
            Arrays.asList("image.jpg", "document.doc", "executable.exe"),
            Arrays.asList("build/files/jpg/image.jpg", "build/files/doc/document.doc", "build/files/exe/executable.exe")
        ),
        Arguments.of(BUILD_FILE_EXTENSION_SORT_BY_EXTENSION,
            new ArrayList<String>(),
            new ArrayList<String>()
        )
    );
  }

  private final static String BASE_BUILD_FILE_CONTENT = """
        plugins {
          id "com.rivancic.files-plugin"
        };
        """;

  private final static String BUILD_FILE_EXTENSION_SORT_BY_ALPHABET = """
        plugins {
          id "com.rivancic.files-plugin"
        };
        
        sortFiles {
          sortType = "alphabet"
        }
        """;

  private final static String BUILD_FILE_EXTENSION_SORT_BY_EXTENSION = """
        plugins {
          id "com.rivancic.files-plugin"
        };
        
        sortFiles {
          sortType = "extension"
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
