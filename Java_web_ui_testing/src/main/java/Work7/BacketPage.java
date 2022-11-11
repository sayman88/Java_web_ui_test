package Work7;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BacketPage extends AbstractPage{
    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    // товар найденный по наименованию
    @FindBy(xpath = "//a/span[text()='Trout Master Ridge Sbiro (12g Floating)']")
    private WebElement currentCommod;

    // кнопка удаления товара
    @FindBy(xpath = "//span[@class='basket-item-actions-remove']")
    private WebElement deleteCommod;

    // итоговая стоимость корзины
    @FindBy(xpath = "//div[@data-entity='basket-total-price']")
    private WebElement currentPrice;

    public BacketPage(WebDriver driver){
        super(driver);
    }

    public String getCommodText()throws InterruptedException{
        js.executeScript("window.scrollTo(0, 400)");
        Thread.sleep(1000);
        return this.currentCommod.getText();
    }

    public BacketPage pressDelCommod() throws InterruptedException{
        this.deleteCommod.click();
        Thread.sleep(2500);
        return this;
    }

    public String getPriceText(){
        return this.currentPrice.getText();
    }


}