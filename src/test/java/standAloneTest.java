import PageObjects.LoginPage;
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

public class standAloneTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.edgedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-notifications");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginPage loginPage=new LoginPage(driver);

        driver.get("https://rahulshettyacademy.com/client");
        String prod="ZARA COAT 3";
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.findElement(By.id("userEmail")).sendKeys("test0211@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Prem@1998");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> items=driver.findElements(By.cssSelector(".mb-3"));
        WebElement product=items.stream().filter(item->item.findElement(By.cssSelector("b")).getText().equals(prod)).findFirst().orElse(null);
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> cart=driver.findElements(By.cssSelector(".cart h3"));
       Boolean cartprod= cart.stream().anyMatch(s-> s.getText().equals(prod));
       Assert.assertTrue(cartprod);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='totalRow']/button")));
       driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
       driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
       List<WebElement> countires=driver.findElements(By.xpath("//section/button/span"));
       WebElement country=countires.stream().filter(s->s.getText().equals("India")).findFirst().orElse(null);
       country.click();
        js.executeScript("window.scrollBy(0,500)");
       driver.findElement(By.cssSelector(".actions a")).click();
       Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        Thread.sleep(5000);

        driver.quit();

    }
}
