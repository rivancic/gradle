package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;


public class FileDirectoryAlphabetMapperTest {

  @ParameterizedTest
  @MethodSource("provideTestFiles")
  void whenFileIsProvided_firstAlphabetOfFilenameIsReturned(File file, String expectedDirectory) {

    // Given
    FileDirectoryAlphabetMapper alphabetMapper = new FileDirectoryAlphabetMapper();

    // When
    String directory = alphabetMapper.getDirectory(file);

    // Then
    Assertions.assertEquals(expectedDirectory, directory);
  }

  private static Stream<Arguments> provideTestFiles() {
    return Stream.of(
        Arguments.of(Path.of("aFirstFile.jpg").toFile(), "a"),
        Arguments.of(Path.of("business.jpg").toFile(), "b"),
        Arguments.of(Path.of("scanner.wav").toFile(), "s"),
        Arguments.of(Path.of("stock.jpg").toFile(), "s")
    );
  }
}
