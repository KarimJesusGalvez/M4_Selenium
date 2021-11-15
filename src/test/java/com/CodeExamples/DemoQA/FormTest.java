package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FormTest {


    private static final String URL = "https://demoqa.com/automation-practice-form";
    WebDriver webdriver;
    JavascriptExecutor js;


    @BeforeEach
    void setUp() {
        String dir = System.getProperty("user.dir"); // ruta del proyecto
        String driverUrl = "/drivers/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);
        webdriver = new ChromeDriver(); // Google Chrome
        js = (JavascriptExecutor) webdriver;
        webdriver.get(URL);
    }

    @AfterEach
    void tearDown() {
        webdriver.quit();
    }


    @Test
    void sectionFirstNameOKTest() {
       webdriver.findElement(By.id("firstName")).sendKeys("EXAMPLE");
        assertEquals("EXAMPLE",webdriver.findElement(By.id("firstName")).getAttribute("value"));

    }

    @Test
    void sectionLastNameTest() {
        webdriver.findElement(By.id("lastName")).sendKeys("LASTEXAMPLE");
        assertEquals("LASTEXAMPLE",webdriver.findElement(By.id("lastName")).getAttribute("value"));

    }
    @Test
    void sectionRadioMaleTest()  {
        webdriver.findElement(By.xpath("//label[@for='gender-radio-1']")).click();
        assertTrue(webdriver.findElement(By.xpath("//label[@for='gender-radio-1']")).isSelected());

    }

    @Test
    void sectionSubjectsTest()  {
        String menucss = ".subjects-auto-complete__menu";
        webdriver.findElement(By.cssSelector("#subjectsInput")).sendKeys("M");
        WebElement menu = webdriver.findElement(By.cssSelector(menucss));
        System.out.println(menu.getAttribute("innerHTML"));
        js.executeScript("arguments[0].scrollIntoView();", menu);

        String mathselector = "//div[text()='Maths' and contains(@class, 'subjects-auto-complete__option')]";
        webdriver.findElement(By.xpath(mathselector)).click();

        String mathdiv = "//div[contains(@class,'css-12jo7m5 subjects-auto-complete__multi-value__label')]";

        assertTrue(webdriver.findElement(By.xpath(mathdiv)).isDisplayed());

    }
    @Test
    void sectionAddArchiveTest()  {

        WebElement upload = webdriver.findElement(By.id("uploadPicture"));
        upload.sendKeys("C:\\data\\Selenium_Logo.png");
    }
    @Test
    void sectionCurrentAdressTest()  {

        WebElement upload = webdriver.findElement(By.id("currentAddress"));

        upload.sendKeys("EXAMPLETEXT");
        assertEquals("EXAMPLETEST",webdriver.findElement(By.id("lastName")).getAttribute("value"));

    }

    @Test
    void sectionStateCityTest()  {

        js.executeScript("arguments[0].scrollIntoView();", webdriver.findElement(By.id("state")));
        webdriver.findElement(By.id("state")).click();

        WebElement statemenu = webdriver.findElement(By.xpath("//div[@id='stateCity-wrapper']//div[contains(@class, 'css-') and contains(@class,'-menu')]"));
        System.out.println(statemenu.getAttribute("innerHTML"));

        webdriver.findElement(By.id("react-select-3-option-2")).click();
        assertTrue(webdriver.findElement(By.cssSelector("#city")).isEnabled());

        webdriver.findElement(By.id("react-select-4-option-2")).click();

    }

    @Test
    void SubmitButton(){

        webdriver.findElement(By.id("submit")).click();
        assertTrue(webdriver.findElement(By.id("closeLargeModal")).isDisplayed());


    }



}

