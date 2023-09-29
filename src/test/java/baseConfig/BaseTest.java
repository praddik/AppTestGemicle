package baseConfig;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeTest
    public void config(){
        Configuration.browser ="chrome";
        Configuration.browserSize ="1920x1080";
        Configuration.headless=false;
    }

    @AfterSuite
    public void closeDriver(){
        Selenide.closeWebDriver();
    }
}
