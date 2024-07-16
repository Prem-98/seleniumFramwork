package PageObjects;

import ReusableComponents.reuse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CatlogPage extends reuse {
    WebDriver driver;

    public CatlogPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css=".mb-3")
    List<WebElement> prods;

    By prod= By.cssSelector(".mb-3");

    By addBtn=By.cssSelector(".card-body button:last-of-type");
    By tostermsg=By.cssSelector("#toast-container");

    @FindBy(css=".ng-animating")
    WebElement animation;



    public List<WebElement> waitForProductsToAppear(){
        waitForElementToAppear(prod);
        return prods;
    }

    public void addProductToCart(String productname){
        WebElement product=waitForProductsToAppear().stream().filter(item->item.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
        product.findElement(addBtn).click();
        waitForElementToAppear(tostermsg);
        waitForWebElementToDisappear(animation);
    }
}
