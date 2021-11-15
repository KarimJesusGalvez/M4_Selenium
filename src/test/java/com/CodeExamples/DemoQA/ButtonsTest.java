package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 *  URL https://demoqa.com/buttons
 */
public class ButtonsTest {

        WebDriver webDriver;
        final String targeturl = "https://demoqa.com/buttons";

        @BeforeEach
        void setUp() {
            String dir = System.getProperty("user.dir");
            String chromeUrl = "/drivers/chromedriver.exe";
            String driverurl = dir + chromeUrl;
            System.setProperty("webdriver.chrome.driver",driverurl);
            webDriver = new ChromeDriver(); // Chrome/Chromium
            webDriver.get(targeturl);
        }
        @AfterEach
        void tearDown() {webDriver.quit();}

        @Test
        void DoubleClickButtonTest(){

            assumeTrue(0 == webDriver.findElements(By.id("doubleClickMessage")).size());

            WebElement button = webDriver.findElement(By.id("doubleClickBtn"));

            /*
            button.click();
            button.click();
            // Not working
             */

            Actions action = new Actions(webDriver);
            action.doubleClick(button).perform();

            WebElement buttonmsg = webDriver.findElement(By.id("doubleClickMessage"));

            assertEquals("You have done a double click",buttonmsg.getText());
        }
    @Test
    void RightClickButtonTest(){


        assumeTrue(0 == webDriver.findElements(By.id("rightClickMessage")).size());

        WebElement button = webDriver.findElement(By.id("rightClickBtn"));


        Actions action = new Actions(webDriver);
        action.contextClick(button).perform();

        WebElement buttonmsg = webDriver.findElement(By.id("rightClickMessage"));

        assertEquals("You have done a right click",buttonmsg.getText());

    }
    @Test
    void dinamicClickButtonTest(){

        assumeTrue(0 == webDriver.findElements(By.id("dynamicClickMessage")).size());

        WebElement button = webDriver.findElement(By.xpath("//button[text() = 'Click Me']"));

            button.click();
        WebElement buttonmsg = webDriver.findElement(By.id("dynamicClickMessage"));

        assertEquals("You have done a dynamic click",buttonmsg.getText());

    }
    }

