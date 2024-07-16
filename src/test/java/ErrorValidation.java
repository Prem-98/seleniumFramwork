import PageObjects.LoginPage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest {

    @Test
    public void errorMsg(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.loginToWebpage("test0211@gmail.com","Prem1998");
        Assert.assertEquals(loginPage.errorValidation(),"Incorrect email or password.");
    }
}
