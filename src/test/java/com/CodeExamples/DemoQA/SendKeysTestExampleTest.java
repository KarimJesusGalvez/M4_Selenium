package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendKeysTestExampleTest {

    // demoqa.com

    // https://seleniumbase.io/demo_page

    WebDriver driver;

    @BeforeEach
    void setup() {
        String driverURL = "D:\\Nueva carpeta\\Curriculum\\Adecco\\Curso Testing Java\\Temario\\Modulo_4_Selenium\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverURL);
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void test1(){
        // https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/

        driver.get("https://seleniumbase.io/demo_page");
        WebElement input = driver.findElement(By.id("myTextinput"));
        input.sendKeys("String sent from Selenium ");
        // sleep()

        String  inputvalue = input.getAttribute("value");
        assertEquals("String sent from Selenium ",inputvalue);

    }
    @Test
    void testGoogleSearch(){
        // https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/

        driver.get("https://google.es");
        WebElement acceptbutton = driver.findElement(By.xpath("//div[text() = 'Acepto']"));
        acceptbutton.click();
        assertEquals("jyfHyd",acceptbutton.getAttribute("class"));
        WebElement input2 = driver.findElement(By.name("q"));

        //input2.sendKeys("Selenium Java Docs" + Keys.ENTER);
        input2.sendKeys("Selenium Java Docs");
        input2.submit();

        sleep();

    }

    @Test
    void testDuckDuckSearch(){
        // https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/

        driver.get("https://duckduckgo.com");
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Selenium Java Docs" + Keys.ENTER);
       sleep();

    }

     static void sleep() {
        try{
            Thread.sleep(10000L);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    @Test
    void testDDropdown(){
        // https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/

        driver.get("https://seleniumbase.io/demo_page");
        WebElement options = driver.findElement(By.cssSelector("@mySelect option"));
       assertEquals(4,options.getSize());
       //List<WebElement> options2 = driver.findElement(By.cssSelector("@mySelect option"));
        //assertEquals(4,options2.size());
        //options2.get(3).click();
        sleep();
    }
    @Test
    void testDDropdownXpath(){
        // https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/

        driver.get("https://seleniumbase.io/demo_page");
        List<WebElement> options = new ArrayList<>();
        options.add(driver.findElement(By.xpath("//select[@id='mySelect']/option[@value='100%']")));
        assertEquals(4,options.size());
        sleep();
    }
}
