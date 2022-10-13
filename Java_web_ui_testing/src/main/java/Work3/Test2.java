package Work3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {
        // негативный сценарий
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
        driver.get("https://ribomaniya.ru/cabinet/auth/?login=yes&backurl=%2F");
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("stendMerlin");
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys("password"); // невалидный пароль
        driver.findElement(By.xpath("//button[@name='Login']")).click();
        String s = driver.findElement(By.xpath("//font[@class='errortext']")).getText();
        assert (s.equals("Неверный логин или пароль."));

        //результат теста
        System.out.println("Тест №2 пройден");    //выведется только если тест не упадет и условия удовлетворят assert

        // выход из браузера
        driver.quit();

    }
}