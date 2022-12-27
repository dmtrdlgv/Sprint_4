package mainPageTests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExtraStepsMainPage {
    WebDriver chromeDriver;



    @Before
    public void setUp() {
        chromeDriver = new ChromeDriver();


    }

    @After
    public void teardown() {
        // Закрыть браузер
        chromeDriver.quit();
    }

}
