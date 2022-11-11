package Work7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage extends AbstractPage{
    // кнопки авторизации и перехода в личный кабинет
    @FindBy(xpath = "//li//a[@href='/cabinet/auth/?login=yes&backurl=%2F']//i")
    private WebElement loginBtt;

    @FindBy(xpath = "//li//a[@href='/cabinet/']//i")
    private WebElement cabinetBtt;

    // имя пользователя
    @FindBy(xpath = "//a[@class='nav-link text-truncate pt-0']")
    private WebElement userName;

    // номер телефона
    @FindBy(xpath = "//div[@class='text-nowrap pe-2']/a")
    private WebElement phoneNum;

    // Заголовок страницы с контентом
    @FindBy(xpath = "//h1")
    private WebElement contentHeader;


    public MainPage (WebDriver driver){
        super(driver);
    }

    public MainPage pressLoginBtt(){
        this.loginBtt.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("/cabinet/auth/?login")); // /cabinet/auth/?login=yes&backurl=%2F
        return this;
    }

    public MainPage pressCabinetBtt(){
        this.cabinetBtt.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("/cabinet/")); // /cabinet/
        return this;
    }

    public Boolean checkUser(String chUserName){
        String s = userName.getText();
        return s.equals(chUserName);
    }

    public String getPhoneNum(){
        return this.phoneNum.getText();
    }

    public MainPage pressMenuItem(String x_path) throws InterruptedException{
        getDriver().findElement(By.xpath(x_path)).click();
        Thread.sleep(1000);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(".ru"));
        return this;
    }

    public Boolean checkMenuItemTitle(String x_path, String title){
        return getDriver()
                .findElement(By.xpath(x_path))
                .getText()
                .equals(title);
    }

    public Boolean checkContentHeader(String header){
        return this.contentHeader.getText().equals(header);
    }



}