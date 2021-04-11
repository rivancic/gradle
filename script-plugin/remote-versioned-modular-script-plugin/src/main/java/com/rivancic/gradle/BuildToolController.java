package com.rivancic.gradle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuildToolController {

    final BuildToolsService buildToolsService;

    public BuildToolController(@Autowired BuildToolsService buildToolsService) {
        this.buildToolsService = buildToolsService;
    }

    @GetMapping("/")
    public ModelAndView redirectWithUsingForwardPrefix() {
        return new ModelAndView("forward:/buildtool");
    }

    @GetMapping("/buildtool")
    public String buildTool(ModelMap model) {
        model.put("buildTools", new BuildToolLandingDTO(buildToolsService.getAll()));
        return "buildtool";
    }

    @PostMapping("/buildtool")
    public String buildToolPost(@ModelAttribute BuildToolLandingDTO buildToolLandingDTO, ModelMap model) {
        System.out.println("Build tool is " + buildToolLandingDTO.getSelectedBuildTool());
        buildToolLandingDTO.setSelectedBuildToolModel(buildToolsService.get(buildToolLandingDTO.getSelectedBuildTool()));
        model.put("buildTools", buildToolLandingDTO);
        return "buildtool";
    }
}
