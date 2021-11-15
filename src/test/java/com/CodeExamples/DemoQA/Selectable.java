package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Selectable {

//    https://demoqa.com/selectable

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
    void clickTest(){
        assertEquals(0,webDriver.findElement(By.cssSelector("#verticalListContainer .active")));

    }
    @Test
    void ClickMultiple(){
        assertEquals(0,webDriver.findElement(By.cssSelector("#verticalListContainer .active")));

    }
}
