package com.rivancic.gradle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BuildToolServiceTest {

    @Test
    void whenGetAllBuildTools_thenReturnAllOfThem() {
        BuildToolsService cut = new BuildToolsService();
        List<BuildTool> allBuildTools = cut.getAll();
        Assertions.assertThat(allBuildTools).hasSize(2);
        Assertions.assertThat(allBuildTools.get(0).getName()).isEqualTo("Maven");
        Assertions.assertThat(allBuildTools.get(0).getScriptLanguages()).hasSize(1);
        Assertions.assertThat(allBuildTools.get(0).getScriptLanguages()).contains("XML");
        Assertions.assertThat(allBuildTools.get(1).getName()).isEqualTo("Gradle");
        Assertions.assertThat(allBuildTools.get(1).getScriptLanguages()).hasSize(2);
        Assertions.assertThat(allBuildTools.get(1).getScriptLanguages()).contains("Groovy", "Kotlin");
    }
}
