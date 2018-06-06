package al.aldi.qa.selenium_tests.utils;

import al.aldi.qa.selenium_tests.tests.BaseTestObject;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestRunner {
    private final Logger logger = LoggerFactory.getLogger(TestRunner.class);
    final String PACKAGE_URL = "al.aldi.qa.selenium_tests.tests";

    public boolean start() {
        boolean result = true;
        List<Class> allTests = getAllTests();

        for (Class test : allTests) {
            logger.info("----------------------------------------------------------------------------");
            logger.info("Starting test: " + test.getName());
            Result runResult = JUnitCore.runClasses(test);
            if (runResult.wasSuccessful()) {
                logger.info("Successful");
            } else {
                logger.error("Failed");
                runResult.getFailures().forEach(System.err::println);
                result = false;
            }
            logger.info("----------------------------------------------------------------------------");
        }

        return result;
    }

    public List<Class> getAllTests() {
        List<Class> result = new ArrayList<>();
        Reflections reflections = new Reflections(getConfigurationBuilder());
        Set<Class<? extends BaseTestObject>> classes = reflections.getSubTypesOf(BaseTestObject.class);
        result.addAll(classes);

        return result;
    }

    public ConfigurationBuilder getConfigurationBuilder() {
        FilterBuilder filterBuilder = new FilterBuilder();

        return new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(PACKAGE_URL));
    }
}
