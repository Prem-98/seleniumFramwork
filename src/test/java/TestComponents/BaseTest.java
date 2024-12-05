package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
//import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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
    public List<HashMap<String, String>> readJasonData(String filePath) throws IOException {

        //below we are reading all the data in jason file to a string
        String jasonContent= FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        //now here after reading the data into a string we are placing it into a Hasmap
        //Using Jacson Databind

        ObjectMapper mapper=new ObjectMapper();

        List<HashMap<String,String>> data=mapper.readValue(jasonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;


    }

}
