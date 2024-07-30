package PageObjects;

import ReusableComponents.reuse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class OrderPage extends reuse {
    WebDriver driver;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> productInOrder;

    public  void validateOrders(String productName){
        boolean product=productInOrder.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(product);

    }



}
