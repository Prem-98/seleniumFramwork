package PageObjects;

import ReusableComponents.reuse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends reuse {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy (id="userEmail")
    WebElement email;

    @FindBy(id="userPassword")
    WebElement password;

    @FindBy(id="login")
    WebElement loginBtn;

    @FindBy(css = "[class*='flyInOut']")
    WebElement error;


    public void loginToWebpage(String username,String pass){
        email.sendKeys(username);
        password.sendKeys(pass);
        loginBtn.click();
    }
    public String errorValidation(){
        waitForWebElementToAppear(error);
        return error.getText();
    }

}
