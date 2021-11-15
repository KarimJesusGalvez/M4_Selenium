package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 *  URL https://demoqa.com/accordian
 */
public class AccordianTest {

        WebDriver webDriver;
        final String targeturl = "https://demoqa.com/accordian";
        JavascriptExecutor js;

        @BeforeEach
        void setUp() {
            String dir = System.getProperty("user.dir");
            String chromeUrl = "/drivers/chromedriver.exe";
            String driverurl = dir + chromeUrl;
            System.setProperty("webdriver.chrome.driver",driverurl);
            webDriver = new ChromeDriver(); // Chrome/Chromium
            webDriver.get(targeturl);
            js = (JavascriptExecutor) webDriver;
        }
        @AfterEach
        void tearDown() {webDriver.quit();}

    @Test
    void section1Test(){
            // Search by 2 or more classes
            assumeTrue(0 == webDriver.findElements(By.cssSelector("accordianContainer.collapse.show")).size());

            WebElement section1 = webDriver.findElement(By.cssSelector("#accordianContainer.collapse.show"));

            WebElement section1Header = webDriver.findElement(By.id("section1Heading"));
            //js.executeScript("arguments[0].scrollIntoView();", section1Header);
            section1.sendKeys(Keys.END);

            section1Header.click();

            assertEquals(0, webDriver.findElements(By.cssSelector("#accordianContainer.collapse.show")).size());

    }
    @Test
    void section2Test(){
        // Search by 2 or more classes
        assumeFalse(webDriver.findElement(By.id("section2Content")).isDisplayed()) ;

        WebElement section2Heading = webDriver.findElement(By.id("section2Heading"));
        WebElement section2 = webDriver.findElement(By.id("section2Content"));

        js.executeScript("arguments[0].scrollIntoView();", section2Heading);

        section2Heading.click();

        section2 = webDriver.findElement(By.id("section2Content"));

        assertTrue( section2.isDisplayed());

        // Wait for clousure
        //Thread.sleep(30000L); + throwable
        // OR
        Boolean isDisplayed = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("section1Content")));

        assertFalse( webDriver.findElement(By.id("section1Content")).isDisplayed());
    }
    }

