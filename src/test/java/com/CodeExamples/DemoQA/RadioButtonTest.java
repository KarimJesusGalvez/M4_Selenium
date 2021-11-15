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
 *  URL https://demoqa.com/checkbox
 */
public class RadioButtonTest {

        WebDriver webDriver;
        final String targeturl = "https://demoqa.com/radio-button";

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
        void submitEmptyFormTest() {
            webDriver.get(targeturl);
            assertEquals(0, webDriver.findElements(By.className("text-success")).size());

            webDriver.findElement(By.className("custom-control-label")).click();

            assertEquals(1, webDriver.findElements(By.className("text-success")).size());
            WebElement textyes = webDriver.findElement(By.className("text-success"));
            assertEquals("Yes", textyes.getText());

            //xpath("//label[@for='impressiveRadio']")
        }


    @Test
    void noRadioTest() {
        webDriver.get(targeturl);

        assertEquals(0, webDriver.findElements(By.className("text-success")).size());

        webDriver.findElement(By.className("custom-control-label")).click();

        assertEquals(1, webDriver.findElements(By.className("text-success")).size());
        WebElement textyes = webDriver.findElement(By.className("text-success"));
        assertEquals(0, webDriver.findElements(By.className("text-success")).size());

    }

        @Test
        void submitFormOKTest(){
        }
    }

