package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Selenium test of
 * https://demoqa.com
 */
public class TextBoxTest {

    WebDriver webDriver;
    final String targeturl = "https://demoqa.com/text-box";

    @BeforeEach
    void setUp() {
        String dir = System.getProperty("user.dir");
        String chromeUrl = "/drivers/chromedriver.exe";
        String driverurl = dir + chromeUrl;
        System.setProperty("webdriver.chrome.driver",driverurl);
        webDriver = new ChromeDriver(); // Chrome/Chromium
    }
    @AfterEach
    void tearDown() {webDriver.quit();}

    @Test
    void submitEmptyFormTest(){
        webDriver.get(targeturl);

        //WebElement undefined = webDriver.findElement(By.className("undefined"))

        assertEquals(1, webDriver.findElements(By.className("undefined")).size());

        //WebElement button = webDriver.findElement(By.className("btn btn-primary"));
        WebElement button = webDriver.findElement(By.id("submit"));
        //SendKeysTestExampleTest.sleep();
        //button.click(); // Shouldn't do anything
        button.submit(); // Shouldn't do anything
        assertEquals(1, webDriver.findElements(By.className("undefined")).size());
        button.getLocation();
    }

    @Test
    void submitFormOKTest(){
        webDriver.get(targeturl);


    }
}
