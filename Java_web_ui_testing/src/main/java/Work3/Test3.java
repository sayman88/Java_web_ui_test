package Work3;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test3 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Java_web_ui_testing/src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // обработка предусловия
        driver.get("https://ribomaniya.ru/?logout=yes");

        // тестовые действия
        driver.get("https://ribomaniya.ru/");
        String s = driver.findElement(By.xpath("//div[@class='text-nowrap pe-2']/a")).getText();
        assert (s.equals("8 (800) 350-32-12"));     // проверка значения по тесткейсу

        //результат теста
        System.out.println("Тест №3 пройден");      // выведется только если тест не упадет и условия удовлетворят
        // assert

        // выход из браузера
        driver.quit();
    }
}