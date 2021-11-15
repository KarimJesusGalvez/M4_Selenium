package GitHub.com;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Download {

    WebDriver chromewebDriver;

    void setup(){
        String dir = System.getProperty("user.dir");
        Path path = Paths.get("C:\\data\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",path.toString());
        chromewebDriver = new ChromeDriver();
        chromewebDriver.get("http://github.com");
    }

    void search(){
       WebElement searchbox = chromewebDriver.findElement(By.xpath("//input[@aria-label='Search GitHub']"));
       searchbox.sendKeys("alansastre");
       searchbox.click();

        WebElement userfilter = chromewebDriver.findElement(By.cssSelector(".menu-item:last-child"));
        userfilter.click();

        // select user, 3 returns img,a,a
        chromewebDriver.findElement(By.xpath("//a[contains(@href, '/alansastre')]")).click();

        assertEquals("",chromewebDriver.getCurrentUrl());

        // select repo
        chromewebDriver.findElements(By.className("UnderlineNav-item")).get(1).click();
        chromewebDriver.findElements(By.xpath("//a[contains(@href, 'proyecto-testing')]")).get(0).click();

        assertEquals("",chromewebDriver.getCurrentUrl());

        // select stars and forks
        chromewebDriver.findElements(By.cssSelector(".social-count")).get(1).click();

        assertEquals("",chromewebDriver.getCurrentUrl());

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

