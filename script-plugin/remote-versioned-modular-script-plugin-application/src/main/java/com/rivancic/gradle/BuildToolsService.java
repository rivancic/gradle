package com.rivancic.gradle;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BuildToolsService {

    List<BuildTool> buildTools = new ArrayList<>();

    public BuildToolsService() {
        BuildTool mavenBuildTool = new BuildTool("Maven", Arrays.asList("XML"));
        BuildTool gradleBuildTool = new BuildTool("Gradle", Arrays.asList("Groovy", "Kotlin"));
        buildTools.add(mavenBuildTool);
        buildTools.add(gradleBuildTool);
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
