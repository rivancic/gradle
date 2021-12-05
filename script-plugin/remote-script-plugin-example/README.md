# Remote Script Plugin Example

This Gradle project shows an example of how **remote script plugin** can be **versioned**. Client has now the control if he wants
to upgrade to the new version of the script plugin, or he wants to use the existing one in case it contains any breaking changes.

Check the [build.gradle](build.gradle) script file to see how the plugin is applied.

`apply from: "https://raw.githubusercontent.com/rivancic/gradle-versioned-script-plugin/${property('tasks.plugin.version')}/gradle/filesPlugin.gradle"`

Source code fo the script plugin is deployed in another publicly available [Github repository](https://github.com/rivancic/gradle-versioned-script-plugin).


## Resources

[Script Plugins (Gradle Userguide)](https://docs.gradle.org/current/userguide/plugins.html#sec:script_plugins)

### Examples

Public GitHub examples using remote Gradle script plugins:

| Project  | Description |
| ------------- | ------------- |
| **<a href="https://github.com/kurra/sampleGradleProject" target="_blank">kurra/sampleGradleProject</a>** | In a simple project, main <a href="https://github.com/kurra/sampleGradleProject/blob/master/build.gradle" target="_blank">gradle.build</a> file applies remote gradle script from another remote repository of <a href="https://github.com/kurra/gradleScripts/blob/master/javaPlugins.gradle" target="_blank">kurra/gradleScripts/</a>. And this remote script in fact applies other remote scripts that set up repositories, other core plugins, manages exclusion of some specific dependencies.. |
| **<a href="https://github.com/grails-guides/grails-elasticsearch/tree/master" target="_blank">grails-guides/grails-elasticsearch</a>** | Repository containing example of Grails integration with Elasticsearch. Root project is applying <a href="https://github.com/grails/grails-guides/blob/master/gradle/guide-build.gradle" target="_blank">remote script plugin</a>. It configures repositories, adds basic tasks and verifies that necessary properties are set after project is configured. If you check other project inside <a href="https://github.com/grails-guides" target="_blank">grails-guides</a> you'll see that almost every project uses same approach to configure basic boilerplate code. |
| **<a href="https://github.com/gocd-contrib/docker-elastic-agents-plugin" target="_blank">gocd-contrib/docker-elastic-agents-plugin</a>** | <a href="https://www.gocd.org/" target="blank">GoCD</a> uses some smart approaches of applying the remote scripts. It uses cache invalidation that appends <a href="https://github.com/gocd-contrib/docker-elastic-agents-plugin/blob/master/build.gradle#L18" target="_blank">time as query parameter</a> to avoid caching old version of the file. This is the <a href="https://github.com/gocd/gocd-plugin-gradle-task-helpers/blob/master/helper.gradle" target="_blank">helper remote script plugin</a> that gets applied. It can also resolve link to the <a href="https://github.com/gocd/gocd-plugin-gradle-task-helpers/blob/master/helper.gradle#L48" target="_blank">file dynamically</a> depending on the fact if its provided as local or remote script plugin. |
