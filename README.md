# test-matcher
Test-Matcher App

This test-matcher app is a Spring Boot application using an H2 Database to store the tester data.

Steps to Configure :

1. CSV files are currently located here: src/main/java/com/applause/test/matcher/testmatcher/utils
2. If adding new files specify in the config in: src/main/resources/application.properties

Steps to Run:

(Pre-req) - Install latest version of Gradle
1. This can be run as a Spring Application in Gradle: run ./gradlew bootRun via command line
2. This will run on by default localhost: 8080
3. API spec is located here: src/main/resources/docs.api.OASv3/test-matcher-api.yml
4. This can be run in Intellij without any configuration required


UI
1. Page has 2 multiple selects for Countries and Device Ids (Press Command or Ctrl Click) 
to select multiple. Pressing Search without selecting any choices will default to All