package Work7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {

    // static WebDriver driver; Ч он оборачиваетс€, поэтому как отдельна€ переменна€ существовать ен будет
    Logger logger = LoggerFactory.getLogger("Test-Case's 1-6");
    static EventFiringWebDriver driver;

    @BeforeAll
    static void initClass() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");


        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new MyWebDriverEventListener());

        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        // стенд медленный, таймауты большиеЕ

    }

    @BeforeEach
    void initTest() {
        driver.get("https://ribomaniya.ru/?logout=yes");
    }

    @AfterAll
    static void finalClass() {
        if(driver !=null) driver.quit();
    }

    public WebDriver getWebDriver(){
        return this.driver;
    }

}