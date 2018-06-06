package al.aldi.qa.selenium_tests;

import al.aldi.qa.selenium_tests.utils.CmdParameterHelper;
import al.aldi.qa.selenium_tests.utils.TestRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Selenium tests boilerplate starting ...");

        boolean success = true;

        CmdParameterHelper parameterHelper = CmdParameterHelper.getInstance();
        boolean shouldExecutionContinue = parameterHelper.parse(args);

        if (shouldExecutionContinue) {
            logger.info("Starting tests");
            TestRunner runner = new TestRunner();
            success = runner.start();
        }

        System.exit(success ? 0 : 1);
    }
}
