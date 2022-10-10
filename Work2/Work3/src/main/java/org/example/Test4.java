package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test4 {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // обработка предусловия
        driver.get("https://ribomaniya.ru/?logout=yes"); // отлогониться

        // тестовые действия
        driver.findElement(By.xpath("//div[@class='col-12']//a[@href='/gruza/']")).click();
        // перейти к грузам (их много, но все ведут в одно место)
        driver.findElement(By.xpath("//div[@class='row']//a[@href='/gruza/bombardy/']")).click();
        // перейти к бомбардам (ссылок много, но все ведут в одно место)
        driver.findElement(By.xpath("//a[@href='/gruza/bombardy/trout-master-ridge-sbiro/']")).click();
        // Выбрать бомбарду (их много, но все ведут в одно место)
        // Нужно сделать скролл, потому что следующий объект скрыт за рамкой окна
        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(1000);
        // явное безусловное ожидание, необходимое для корректной работы теста на медленных и слабых стендах.
        driver.findElement(By.xpath("//td//strong[text()='12g Floating']/../..")).click();
        // Выбрать модель 12 грамм
        // но для этого нужно снова сделать скрол, ибо элемент за рамкой окна.
        js.executeScript("window.scrollTo(0, 700)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='tocart[add]']")).click();
        // Добавить в корзину
        driver.get("https://ribomaniya.ru/cabinet/basket/");
        // Переход в корзину прямым переходом к странице
        js.executeScript("window.scrollTo(0, 500)");
        Thread.sleep(1000);
        // снова вертикальный скролл…
        String s = driver.findElement(By.xpath("//a[@href='/gruza/bombardy/trout-master-ridge-sbiro/trout-master-ridge-sbiro-12g-floating/']")).getText();
        // Получил текст товарной позиции
        assert (s.equals("Trout Master Ridge Sbiro (12g Floating)"));
        //проверка факта наличия в корзине товара с таким названием
        driver.findElement(By.xpath("//span[@class='basket-item-actions-remove']")).click();
        // Удаление товара из корзины (он там один)
        String s1 = driver.findElement(By.xpath("//div[@data-entity='basket-total-price']")).getText();
        // Получение итоговой стоимости корзины (там 0р.)
        assert (s1.equals("0 р."));
        // проверка факта нулевой стоимости корзины

        //результат теста
        System.out.println("Тест №4 пройден");      // выведется только если тест не упадет и условия удовлетворят

        // выход из браузера
        driver.quit();
    }
}