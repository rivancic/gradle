# Gradle Versioned Modular Remote Script Plugin Example

This source code is part of [Gradle build tool example projects](https://github.com/rivancic/gradle).

It contains Gradle script modular plugin that will be used in the lecture explaining script plugins.

In concrete example the emphasis is put on the plugin modularity.
This is logical next step after extracting build logic from the main build.gradle file.
Its guaranteed that the build logic in bigger project will expand with specific requirements we have to be able
to separate different concerns of building process into separate files.

For example, for very simple project maybe is enough to only compile and package the code into a jar file.
Compilation of the source code is dependent on the language its written in.
Packaging of the application highly depends on the type of the framework the application was written in.

Often times one of the basic requirements is that the source code is tested with tests that can be applied on different layers.

This means additionally to compilation of source code and packaging the application we also have to support different kinds of 
source code or application testing.

Unit testing (Junit, TestNG, ..)
Integration testing (Wiremock, In memory databases, SpringBoot testing, Testcontainers)
E2E tests (Selenium, ...)

Building process can be expanded with plethora of other intermediary steps that help software engineers...


