package ReusableComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLInputElement;

import java.time.Duration;

public class reuse {
    //This is the class file contains  all locators and methods which we will be using in our page files . These locators and methods are at
    // global level so that we can just call this class and use the methods and locators present in this class
    WebDriver driver;

    public reuse(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

   @FindBy(css = "[routerlink*='cart']")
   WebElement cart;

    @FindBy(css = "[routerlink='/dashboard/myorders']")
    WebElement orders;

    public void waitForElementToAppear(By element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void waitForWebElementToAppear(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForWebElementToDisappear(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public void goToCart(){
        cart.click();

    }
    public void goToOrders(){
        orders.click();
    }
    public void scrollWebPage(String scroll){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+scroll+")");

    }


}
