Feature: Selecting build tool on landing page shows compatible script languages

  Scenario: Select Maven build tool
    Given application is started
    When Maven build tool is selected
    Then 'XML' as script language is available

  Scenario: Select Gradle build tool
    Given application is started
    When Gradle build tool is selected
    Then 'Groovy, Kotlin' as script language is available