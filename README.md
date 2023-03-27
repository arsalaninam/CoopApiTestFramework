# API Testing Framework

This Framework is designed for Backend Testing of Webservices using Java with RestAssured and TestNG utilizing Maven as dependency management tool. Data Driven Test strategy is used to design and execute tests utilizing TestNG Data Providers.

## Technology Stack

- Java
- TestNG
- Maven
- RestAssured

## Prerequisites

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Dev Kit
* [Maven](https://maven.apache.org/download.cgi) - Dependency Manager
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download) - IDE

## Project Structure

>.circleci: contains CircleCi confiurations to execute tests on CircleCi Environment

>businesslayer: This directory contains all the business logics to retrieve pojo response

>constant: This package contains all the required constants of services, scenario names etc.

>data: This directory contains TestNG Data Provider Classes

>pojo: This directory contains all the response pojos of each json placeholder endpoint

>util: This package contains Utility Classes

>config: This package contains configurations, service endpoints and baseUrl

>testcase: This directory contains test cases for each coop api endpoint

## Getting Started

We are using COOP APIs as Application Under Test.

* URL : http://coop.apps.symfonycasts.com/api

Following instructions will help you running the project. First of all, checkout/clone this project from master branch on your local machine.

### Installation

Open the project in IntelliJ. Run the following command in Terminal and build the project. It will automatically download all the required dependencies.

```sh
$ mvn clean install
```

If the build is successful. All the required dependencies are installed successfully. But if the build fails, make sure to to resolve all the issues in order to execute tests successfully. Make sure that config.properties path in Property Reader class is set according to your Operating System Environment.

### Execute Tests

Run the following command in Terminal to execute tests.

```sh
$ mvn clean test
```

Or Run the following command in Terminal to execute tests with testng.xml

```sh
$ mvn clean test -DsuiteXmlFile=testng.xml
```

### Test Report

You can find the Surefire HTML reports in the following directory of the Project.

```sh
\target\surefire-reports\index.html
```

Under the surefire-reports directory, open ‘index.html’ file to view reports.
