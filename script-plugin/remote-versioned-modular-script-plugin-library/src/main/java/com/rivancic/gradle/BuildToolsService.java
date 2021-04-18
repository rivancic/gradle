package com.rivancic.gradle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BuildToolsService {

    List<BuildTool> buildTools = new ArrayList<>();

    public BuildToolsService() {
        BuildTool mavenBuildTool = new BuildTool("Maven", Collections.singletonList("XML"));
        BuildTool gradleBuildTool = new BuildTool("Gradle", Arrays.asList("Groovy", "Kotlin"));
        BuildTool sbtBuildTool = new BuildTool("sbt", Collections.singletonList("Scala"));
        buildTools.add(mavenBuildTool);
        buildTools.add(gradleBuildTool);
        buildTools.add(sbtBuildTool);
    }

    public List<BuildTool> getAll() {
        return buildTools;
    }

    public BuildTool get(String selectedBuildTool) {
        return buildTools.stream()
                .filter(tool -> tool.getName().equals(selectedBuildTool))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nonexisting build tool"));
    }
}
