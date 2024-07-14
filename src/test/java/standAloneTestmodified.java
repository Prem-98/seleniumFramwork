import PageObjects.CatlogPage;
import PageObjects.LoginPage;
import PageObjects.PaymentPage;
import PageObjects.cartPage;
import ReusableComponents.reuse;
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

import java.time.Duration;
import java.util.List;

public class standAloneTestmodified {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.edgedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-notifications");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginPage loginPage=new LoginPage(driver);
        CatlogPage catlogPage=new CatlogPage(driver);
        reuse reuse=new reuse(driver);
        cartPage cartPage=new cartPage(driver);
        PaymentPage paymentPage=new PaymentPage(driver);

        driver.get("https://rahulshettyacademy.com/client");
        String prod="ZARA COAT 3";

        loginPage.loginToWebpage("test0211@gmail.com","Prem@1998");


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
        Thread.sleep(5000);

        driver.quit();

    }
}
