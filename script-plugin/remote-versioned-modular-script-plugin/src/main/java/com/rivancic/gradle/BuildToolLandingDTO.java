package com.rivancic.gradle;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BuildToolLandingDTO {

    private List<BuildTool> buildToolList;
    private String selectedBuildTool = null;
    private BuildTool selectedBuildToolModel;

    public String getBuildScriptLanguagesAsText() {
        return selectedBuildToolModel.getScriptLanguages().stream().collect(Collectors.joining(", "));
    }

    public BuildToolLandingDTO() {
        this.buildToolList = Collections.emptyList();
    }

    public BuildToolLandingDTO(List<BuildTool> buildToolList) {
        this.buildToolList = buildToolList;
    }

    public BuildTool getSelectedBuildToolModel() {
        return selectedBuildToolModel;
    }

    public void setSelectedBuildToolModel(BuildTool selectedBuildToolModel) {
        this.selectedBuildToolModel = selectedBuildToolModel;
    }

    public List<BuildTool> getBuildToolList() {
        return buildToolList;
    }

    public void setBuildToolList(List<BuildTool> buildToolList) {
        this.buildToolList = buildToolList;
    }

    public String getSelectedBuildTool() {
        return selectedBuildTool;
    }

    public void setSelectedBuildTool(String selectedBuildTool) {
        this.selectedBuildTool = selectedBuildTool;
    }
}
