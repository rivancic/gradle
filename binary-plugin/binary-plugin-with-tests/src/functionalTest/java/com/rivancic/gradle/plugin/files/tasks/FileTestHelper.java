package com.rivancic.gradle.plugin.files.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

public class FileTestHelper {

  private final File testProjectDir;

  public FileTestHelper(File testProjectDir) {
    this.testProjectDir = testProjectDir;
  }

  public File createNewFileWithContent(String file, String content) throws IOException {
    File newFile = new File(testProjectDir, file);
    writeFile(newFile, content);
    return newFile;
  }

  public File createDirectory(String directory) {
    File newDirectory = new File(testProjectDir, directory);
    newDirectory.mkdirs();
    return newDirectory;
  }

  void printAllFilesIn(File file) {
    Arrays.stream(file.listFiles()).forEach(fil ->
        System.out.println(fil.getName())
    );
  }

  public void printAllFilesIn(String filePath) {
    File directoryToPrint = new File(testProjectDir, filePath);
    if(directoryToPrint.exists()) {
      Arrays.stream(directoryToPrint.listFiles()).forEach(file ->
          System.out.println(" - "+ file.getName())
      );
    }
  }

  void writeFile(File destination, String content) throws IOException {
    try (BufferedWriter output = new BufferedWriter(new FileWriter(destination))) {
      output.write(content);
    }
  }

  public void removeDirectory(String filePath) throws IOException {
    File directoryToRemove = new File(testProjectDir, filePath);
    Files.walk(directoryToRemove.toPath())
        .sorted(Comparator.reverseOrder())
        .map(Path::toFile)
        .forEach(File::delete);
  }
}
