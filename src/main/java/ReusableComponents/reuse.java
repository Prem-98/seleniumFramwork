package ReusableComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLInputElement;

import java.time.Duration;

public class reuse {
    WebDriver driver;

    public reuse(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

   @FindBy(css = "[routerlink*='cart']")
   WebElement cart;

    public void waitForElementToAppear(By element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void waitForElementToDisappear(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public void goToCart(){
        cart.click();

    }
    public void scrollWebPage(String scroll){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+scroll+")");

    }


}
