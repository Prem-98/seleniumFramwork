import PageObjects.*;
import ReusableComponents.reuse;
import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class standAloneTestmodified extends BaseTest {

    @Test (dataProvider = "getDataMap")
    public void addToCart(HashMap<String,String>input) throws IOException, InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        CatlogPage catlogPage=new CatlogPage(driver);
        reuse reuse=new reuse(driver);
        cartPage cartPage=new cartPage(driver);
        PaymentPage paymentPage=new PaymentPage(driver);
        String prod=input.get("product");

        loginPage.loginToWebpage(input.get("email"),input.get("pass"));


        catlogPage.addProductToCart(prod);

        reuse.goToCart();

        List<WebElement> cart=driver.findElements(By.cssSelector(".cart h3"));
       Boolean cartprod= cart.stream().anyMatch(s-> s.getText().equals(prod));
       Assert.assertTrue(cartprod);
       Thread.sleep(4000);

        reuse.scrollWebPage("1000");
        Thread.sleep(2000);

       cartPage.goToCheckOut();
        paymentPage.selectCountry("India");
        reuse.scrollWebPage("700");
        paymentPage.clickOnPlaceOrder();
       Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        Thread.sleep(2000);

    }
    @Test (dependsOnMethods = {"addToCart"})
    public void orderHistory(){
        LoginPage loginPage=new LoginPage(driver);
        OrderPage orderPage=new OrderPage(driver);
        loginPage.loginToWebpage("test0211@gmail.com","Prem@1998");
        orderPage.goToOrders();
        orderPage.validateOrders("ZARA COAT 3");

    }

    @DataProvider
    public Object [][] getDataMap(){
        HashMap<String,String>map=new HashMap<String,String>();
        map.put("email","test0211@gmail.com");
        map.put("pass","Prem@1998");
        map.put("product","ZARA COAT 3");

        HashMap<String,String>map2=new HashMap<String,String>();
        map2.put("email","test0211@gmail.com");
        map2.put("pass","Prem@1998");
        map2.put("product","ADIDAS ORIGINAL");

            return new Object [][]{{map},{map2}};

}
    @DataProvider
    public Object [][] getDataSimple(){

        return new Object [][]{{"test0211@gmail.com","Prem@1998","ZARA COAT 3"},{"test0211@gmail.com","Prem@1998","ADIDAS ORIGINAL"}};

    }}
