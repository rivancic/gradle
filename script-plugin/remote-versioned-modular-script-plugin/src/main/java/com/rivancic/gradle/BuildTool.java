package com.rivancic.gradle;

import java.util.List;

public class BuildTool {
    private String name;
    private List<String> scriptLanguages;

    public BuildTool(String name, List<String> scriptLanguages) {
        this.name = name;
        this.scriptLanguages = scriptLanguages;
    }


    public String getName() {
        return name;
    }

    public List<String> getScriptLanguages() {
        return scriptLanguages;
    }
}
