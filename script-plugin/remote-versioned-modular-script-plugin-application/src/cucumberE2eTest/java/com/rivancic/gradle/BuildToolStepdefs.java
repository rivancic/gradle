package com.rivancic.gradle;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BuildToolStepdefs extends BuildToolSpringIntegrationTest {

    WebDriver driver;

    @Given("application is started")
    public void application_starts() {
        driver = new ChromeDriver();
        String url = "http://localhost:" + port + "/";
        driver.get(url);
    }

    @When("{word} build tool is selected")
    public void build_tool_is_selected(String buildTool) {
        driver.findElement(By.xpath("//option[@value='"+buildTool+"']")).click();
        driver.findElement(By.xpath("//input[@value='Select']")).click();
    }

    @Then("{string} as script language is available")
    public void check_which_script_languages_are_available(String availableScripLanguages) {
        String displayedText = driver.findElement(By.xpath("//p")).getText();
        displayedText.endsWith(availableScripLanguages);
        driver.close();
    }
}
