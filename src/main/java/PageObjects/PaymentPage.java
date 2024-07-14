package PageObjects;

import ReusableComponents.reuse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage extends reuse {
    WebDriver driver;

    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryDD;

    @FindBy(xpath = "//section/button/span")
    List<WebElement> countires;

    @FindBy(css = ".actions a")
    WebElement placeOrder;

    public void selectCountry(String country){
        countryDD.sendKeys(country);
        WebElement c=countires.stream().filter(s->s.getText().equals(country)).findFirst().orElse(null);
        c.click();
    }

    public void clickOnPlaceOrder(){
        placeOrder.click();
    }



}
