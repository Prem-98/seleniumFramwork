package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    // this class file contains the basic methods which will be used in all the test class files
    // like intiatization of dirver , launching the website , killing the driver after the test case is done
    public WebDriver driver;
    public WebDriver initializDriver() throws IOException {

        Properties global=new Properties();
        FileInputStream fs=new FileInputStream("src/main/java/Resourses/Global.properties");
        global.load(fs);
        String browser=global.getProperty("Browser");
        if(browser.equalsIgnoreCase("Chrome")){
            WebDriverManager.edgedriver().setup();
            driver=new ChromeDriver();

        }
        driver.manage().window().maximize();
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-notifications");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
    @BeforeMethod
    public WebDriver launchApplication() throws IOException {
        WebDriver driver=initializDriver();
        driver.get("https://rahulshettyacademy.com/client");
        return driver;
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
