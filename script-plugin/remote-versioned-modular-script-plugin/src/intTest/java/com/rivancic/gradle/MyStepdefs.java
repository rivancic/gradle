package com.rivancic.gradle;

import io.cucumber.java8.En;

public class MyStepdefs implements En {
    public MyStepdefs() {
        Given("^application is started$", () -> {
            System.out.println("Application is started");
        });
        When("^Maven build tool is selected$", () -> {
            System.out.println("Maven build tool is selected");
        });
        Then("^only XML as script language is available$", () -> {
            System.out.println("only XML as script language is available");
        });
    }
}
