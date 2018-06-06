package al.aldi.qa.selenium_tests.tests;

import al.aldi.qa.selenium_tests.utils.BrowserUtils;
import al.aldi.qa.selenium_tests.utils.CmdParameterHelper;
import al.aldi.qa.selenium_tests.utils.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTestObject {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public TestConfig config = CmdParameterHelper.getInstance().getConfig();

    protected WebDriver driver = BrowserUtils.getChromeDriver();

    @Before
    public void setUp() {
        // initializes annotation based elements
        PageFactory.initElements(driver, this);
        // TODO: implement setup
    }

    @Test
    public abstract void test();

    @After
    public void tearDown() {
        // TODO: implement tear down
        driver.close();
    }

    // ------------------------------------------------------------------------

    public TestConfig getConfig() {
        return config;
    }

    public void setConfig(TestConfig config) {
        this.config = config;
    }

    // ------------------------ HELPER ----------------------------------------

    public void logTrace(String logMessage) {
        logger.trace(logMessage);
    }

    public void logDebug(String logMessage) {
        logger.debug(logMessage);
    }

    public void logInfo(String logMessage) {
        logger.info(logMessage);
    }

    public void logWarn(String logMessage) {
        logger.warn(logMessage);
    }

    public void logError(String logMessage) {
        logger.error(logMessage);
    }
}
