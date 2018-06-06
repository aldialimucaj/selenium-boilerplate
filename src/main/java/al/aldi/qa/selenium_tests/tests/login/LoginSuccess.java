package al.aldi.qa.selenium_tests.tests.login;

import al.aldi.qa.selenium_tests.tests.BaseTestObject;
import al.aldi.qa.selenium_tests.utils.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginSuccess extends BaseTestObject {

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "ajax-button")
    private WebElement submit;


    @Override
    public void test() {
        driver.get(config.getUrl());

        email.sendKeys(config.getUsername());
        password.sendKeys(config.getPassword());

        submit.click();

        BrowserUtils.sleep();

        Assert.assertNotEquals("URL should have changed after login",config.getUrl(), driver.getCurrentUrl());
    }
}
