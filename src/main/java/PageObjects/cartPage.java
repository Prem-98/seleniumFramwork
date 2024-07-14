package PageObjects;

import ReusableComponents.reuse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPage extends reuse {
    WebDriver driver;

    public cartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css = ".totalRow button")
    WebElement checkOut;

    public  void goToCheckOut(){
        checkOut.click();

    }



}
