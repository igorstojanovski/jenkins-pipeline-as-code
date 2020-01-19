# Description

This is a minimal Spring Boot project that is to intended to demonstrate a few things. This project is used in combination with
[jenkins-auto-configure](https://github.com/igorstojanovski/jenkins-auto-configure), a Jenkins Docker image project.

## Jenkinsfile

In the root of the project there is a Jenkinsfile. It can be used to create a Jenkins pipeline. **jenkins-auto-configure** will pick up this image and start a pipeline based on it.
The configuration in the Jenkinsfile will create a basic pipeline that will build in project and then start two parallel branches. One will run the unit tests and the other will run the integration tests.
This is how the pipeline will look like in Blue Ocean:
![Blue Ocean Pipeline][blue-ocean-pipeline]

## Unit and Integration tests

The test classes demonstrate how to run very basic unit and integration test using JUnit 5. The integration tests have different setup that the unit test. Which is understandable because they need to setup the whole context before running.

## Filtering tests with Gradle

In the docker.build file there are two new tasks that filter tests based on tags. The tasks here are used to separate unit from integration tests so they can be run in parallel.

# TODO
In order to create a full fledged replica of a production pipeline a few more steps need to be implimented:
* Generate coverage reports
* Do a code scan with SonarCloud
* Upload test reports
* Deploy
* Run a smoke test (hint: Postman)

[blue-ocean-pipeline]: https://igorski.co/wp-content/uploads/2020/01/BlueOcean_Example.png
