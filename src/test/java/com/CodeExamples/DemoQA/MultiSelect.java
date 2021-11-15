package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MultiSelect {

    WebDriver webDriver;
    final String targeturl = "https://demoqa.com/select-menu";
    JavascriptExecutor js;


    @BeforeEach
    void setUp() {
        String dir = System.getProperty("user.dir");
        String chromeUrl = "/drivers/chromedriver.exe";
        String driverurl = dir + chromeUrl;
        System.setProperty("webdriver.chrome.driver", driverurl);
        webDriver = new ChromeDriver(); // Chrome/Chromium
        webDriver.get(targeturl);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    void Select2() {

        WebElement multiSelect = webDriver.findElement(By.id("cars"));
        js.executeScript("arguments[0].scrollIntoView();", multiSelect);


        WebElement volvo = webDriver.findElement(By.xpath("//option[@value='volvo']"));
        WebElement input2 = webDriver.findElement(By.xpath("//option[@value='saab']"));



        Actions action = new Actions(webDriver);
        action.keyDown(Keys.CONTROL).click(input2).perform();
    }
    @Test
    void SelectAll() {

        WebElement multiSelect = webDriver.findElement(By.id("cars"));
        js.executeScript("arguments[0].scrollIntoView();", multiSelect);


        List<WebElement> elements = webDriver.findElements(By.xpath("//select[@id='cars']/option"));

        for(WebElement element: elements);
        Actions action = new Actions(webDriver);
        //action.keyDown(Keys.CONTROL).click(element).perform();
    }
}
