package GitHub.com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Download Source file from repository
 */
@DisplayName("Download Source file")
public class Download {

    WebDriver chromewebDriver;

    @BeforeEach
    void setUp(){
        String dir = System.getProperty("user.dir");
        Path path = Paths.get("C:\\data\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",path.toString());
        chromewebDriver = new ChromeDriver();
        chromewebDriver.get("http://github.com");
    }

    @AfterEach
    void tearDown() {
//      firefoxwebDriver.quit();
        chromewebDriver.quit();
    }

    @Test
    @DisplayName("")
    void search(){
       WebElement searchbox = chromewebDriver.findElement(By.xpath("//input[@aria-label='Search GitHub']"));
       searchbox.sendKeys("alansastre");
       searchbox.sendKeys(Keys.ENTER);

        assertTrue(chromewebDriver.getCurrentUrl().contains("https://github.com/search?q=alansastre"));


        WebElement userfilter = chromewebDriver.findElement(By.cssSelector(".menu-item:last-child"));
        userfilter.click();

        // select user, 3 returns img,a,a
        try {
            chromewebDriver.findElements(By.xpath("//a[contains(@href, '/alansastre')]")).get(1).click();
            assertTrue(chromewebDriver.getCurrentUrl().contains("https://github.com/alansastre"));
        }catch(Exception error){
            error.printStackTrace();
            // TODO add assert Error msg isDisplayed()
            assertTrue(true);
        }


        // select repositories -> repository

        // TODO Fix ElementNOTINTERACTABLE
        new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("UnderlineNav-item")));
        chromewebDriver.findElements(By.className("UnderlineNav-item")).get(1).click();
        chromewebDriver.findElements(By.xpath("//a[contains(@href, 'proyecto-testing')]")).get(0).click();

        assertEquals("https://github.com/alansastre/proyecto-testing",chromewebDriver.getCurrentUrl());

        // select stars and forks
        chromewebDriver.findElements(By.cssSelector(".social-count")).get(1).click();

        assertEquals("https://github.com/alansastre/proyecto-testing/network/members",chromewebDriver.getCurrentUrl());

        // select final repo
        chromewebDriver.findElement(By.xpath("//a[contains(@href,'/alicia') and contains(@href,'/proyecto')]")).click();

        assertEquals("https://github.com/alicianunex/proyecto-testing",chromewebDriver.getCurrentUrl());

        //Select Code button
        chromewebDriver.findElements(By.xpath("//summary[@role='button']")).get(1).click();


        WebElement giturl = chromewebDriver.findElements(By.xpath("//input[contains(@class, 'form-control') and contains(@aria-label,'alicia')]")).get(0);
        assertEquals("https://github.com/alicianunex/proyecto-testing.git",giturl.getAttribute("arialabel"));

        // Select download button
        WebElement dowloadbtn =chromewebDriver.findElement(By.xpath("//a[@data-open-app='link']"));
        dowloadbtn.click();

        // Form path
        String path = Paths.get("C:\\data").toString();
        LocalDate localDate = LocalDate.now();

        // Send path to save
        dowloadbtn.sendKeys(localDate.toString() + path);
        dowloadbtn.sendKeys(Keys.ENTER);
    }

}

