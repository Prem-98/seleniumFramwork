import PageObjects.CatlogPage;
import PageObjects.LoginPage;
import PageObjects.PaymentPage;
import PageObjects.cartPage;
import ReusableComponents.reuse;
import TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test
    public void errorMsg(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.loginToWebpage("test0211@gmail.com","Prem1998");
        Assert.assertEquals(loginPage.errorValidation(),"Incorrect email or password.");
    }
    @Test
    public void productErrorvalidation() throws IOException, InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        CatlogPage catlogPage=new CatlogPage(driver);
        reuse reuse=new reuse(driver);
        cartPage cartPage=new cartPage(driver);
        PaymentPage paymentPage=new PaymentPage(driver);
        String prod="ZARA pen 3";
        loginPage.loginToWebpage("test0211@gmail.com","Prem@1998");
        catlogPage.addProductToCart(prod);
        reuse.goToCart();
}
}
