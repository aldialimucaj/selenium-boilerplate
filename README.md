# Selenium Boilerplate
Boilerplate aimed to help get started as fast as possible with your selenium test project.

While developing every test case can be executed separately from the bundle.
Once you want to execute the test from a CI you have to build a bundled jar file.

Note: the filename of your jar file is dependent on you project name

## Dependencies

* Java JDK 8 or newer
* Gradle 4 or newer
* You need to have installed [chromedriver](http://chromedriver.chromium.org/home)

## Build

```./gradlew shadowJar```

## Getting started
You can execute the compiled jar file by runnig this command 

```java -jar build/libs/selenium-boilerplate-all.jar ```

### Configuration
In order to start test execution config files are needed. 
Out of the box config files have defined only URL, username and
password fields which you can extend at your comfort.

```
{
  "url": "http://localhost:8080",
  "username": "admin@example.com",
  "password": "***"
}
```

You can generate a sample config file by execution

```java -jar build/libs/selenium-boilerplate-all.jar --generate-config```

## Execution
Test execution is done by calling 

```java -jar build/libs/selenium-boilerplate-all.jar -c config.json```

## Options
Currently only Chrome Browser is supported
