package com.rivancic.gradle.plugin.files.tasks.clean;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CleanTaskTest {

  @TempDir
  File testProjectDir;
  private File settingsFile;
  private File buildFile;
  private File filesDirectory;
  private File fileToSort;

  @BeforeEach
  public void setup() {
    settingsFile = new File(testProjectDir, "settings.gradle");
    buildFile = new File(testProjectDir, "build.gradle");
    filesDirectory = new File(testProjectDir, "files");
    filesDirectory.mkdirs();
    fileToSort = new File(filesDirectory, "image.jpg");
  }

  @Test
  public void testHelloWorldTask() throws IOException {
    writeFile(settingsFile, "rootProject.name = 'filesPlugin'");
    String buildFileContent = "plugins { " +
          "id \"com.rivancic.files-plugin\" " +
        "}; ";
    writeFile(buildFile, buildFileContent);
    writeFile(fileToSort, "test");

    BuildResult result = GradleRunner.create()
        .withProjectDir(testProjectDir)
        .withArguments("sortFiles")
        .withPluginClasspath()
        .build();

    System.out.println(result.getOutput());

    // Print content of build directory
    System.out.println("// absolute path ///");
    System.out.println(testProjectDir.getAbsolutePath());
    System.out.println("// root ///");
    Arrays.stream(testProjectDir.listFiles()).forEach(fil ->
       System.out.println(fil.getName())
    );
    System.out.println("// root/files ///");
    Arrays.stream(new File(testProjectDir,"files").listFiles()).forEach(fil ->
        System.out.println(fil.getName())
    );
    System.out.println("// build ///");
    Arrays.stream(new File(testProjectDir, "build/files").listFiles()).forEach(fil ->
        System.out.println(fil.getName())
    );
    // END Print content of build directory

    Assertions.assertEquals(SUCCESS, result.task(":sortFiles").getOutcome());
  }

  private void writeFile(File destination, String content) throws IOException {
    BufferedWriter output = null;
    try {
      output = new BufferedWriter(new FileWriter(destination));
      output.write(content);
    } finally {
      if (output != null) {
        output.close();
      }
    }
  }
}
