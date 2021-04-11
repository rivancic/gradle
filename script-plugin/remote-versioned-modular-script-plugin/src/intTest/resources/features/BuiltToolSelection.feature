Feature: Selecting build tool shows compatible script languages

    Scenario: Select Maven build tool
    Given application is started
    When Maven build tool is selected
    Then only XML as script language is available