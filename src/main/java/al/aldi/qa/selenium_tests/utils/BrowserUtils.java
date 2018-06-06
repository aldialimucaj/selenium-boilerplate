package al.aldi.qa.selenium_tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserUtils {
    public static final long SLEEP_TIME_DEFAULT = 1000;

    /**
     * Set current Thread sleeping for specified number of milliseconds.
     *
     * @param sleepTime time in millis to sleep.
     */
    public static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep() {
        sleep(SLEEP_TIME_DEFAULT);
    }

    public static WebDriver getChromeDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addArguments("disable-infobars");
        options.addArguments("window-size=1920x1080");

        return new ChromeDriver(options);
    }

    public static WebDriver getRemoteWebDriver(String address, String port, boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addArguments("disable-infobars");
        options.addArguments("window-size=1920x1080");

        if (headless) {
            options.addArguments("headless");
        }

        WebDriver remoteDriver = null;
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            remoteDriver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", address, port)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return remoteDriver;
    }
}
