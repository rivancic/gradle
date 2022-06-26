package com.rivancic.gradle.plugin.files.tasks.sort.mapper;

import org.gradle.api.InvalidUserDataException;
import org.gradle.api.internal.provider.Providers;
import org.gradle.api.logging.Logger;
import org.gradle.api.provider.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FileDirectoryMapperFactoryTest {

  @Test
  void whenNoSortType_thenReturnDefaultFileDirectoryMapper() {

    // Given
    Provider<String> sortTypeProvider = Providers.notDefined();
    Logger logger = null;

    // When
    FileDirectoryMapper fileDirectoryMapper = FileDirectoryMapperFactory.getFileDirectoryMapper(logger, sortTypeProvider);

    // Then
    Assertions.assertEquals(fileDirectoryMapper.getClass(), FileDirectoryDateMapper.class);
  }

  @ParameterizedTest
  @MethodSource("provideSortTypeWithExpectedFileMapper")
  void whenSpecificSortType_thenReturnSpecificFileDirectoryMapper(String sortingType, Class<?> expectedFileDirectoryMapper) {

    // Given
    Provider<String> sortTypeProvider = Providers.of(sortingType);
    Logger logger = null;

    // When
    FileDirectoryMapper fileDirectoryMapper = FileDirectoryMapperFactory.getFileDirectoryMapper(logger, sortTypeProvider);

    // Then
    Assertions.assertEquals(expectedFileDirectoryMapper, fileDirectoryMapper.getClass());
  }

  @Test
  void whenExtensionOfWrongSortType_thenThrowInvalidUserDataException() {

    // Given
    Provider<String> sortTypeProvider = Providers.of("wrong_sort_type");
    Logger logger = null;

    // When
    InvalidUserDataException exception = Assertions.assertThrows(InvalidUserDataException.class, () -> {
      FileDirectoryMapper fileDirectoryMapper = FileDirectoryMapperFactory.getFileDirectoryMapper(logger, sortTypeProvider);
    });

    // Then
    Assertions.assertEquals("Invalid property sortType value provided [wrong_sort_type]. Valid values are ['extension','date','alphabet']",exception.getMessage());
  }

  private static Stream<Arguments> provideSortTypeWithExpectedFileMapper() {
    return Stream.of(
        Arguments.of("extension", FileDirectoryExtensionMapper.class),
        Arguments.of("alphabet", FileDirectoryAlphabetMapper.class),
        Arguments.of("date", FileDirectoryDateMapper.class)
    );
  }
}
