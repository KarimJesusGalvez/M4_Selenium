package com.CodeExamples.OpenMRS;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Form extends BaseTest{

//    https://demo.openmrs.org/openmrs/login.htm

        final String targeturl = "https://demo.openmrs.org/openmrs/login.html";
        final static String USERNAME = "Admin";
        final static String PASSWORD = "Admin123";



        @BeforeEach
        void setUp() {
            String dir = System.getProperty("user.dir");
            String chromeUrl = "/drivers/chromedriver.exe";
            String driverurl = dir + chromeUrl;
            System.setProperty("webdriver.chrome.driver", driverurl);
            webDriver = new ChromeDriver(); // Chrome/Chromium
            webDriver.get(targeturl);
            LoginAdminPharmacy();
        }

        @AfterEach
        void tearDown() {
            webDriver.quit();
        }

        @Test
        void LoginOKTest() {


            webDriver.findElement(By.id("username")).sendKeys(USERNAME);

            webDriver.findElement(By.id("username")).sendKeys(PASSWORD);

            webDriver.findElement(By.id("Pharmacy")).click();

            webDriver.findElement(By.id("loginButton")).click();


            assertEquals("Logged in as Super User (admin) at Pharmacy.",
                    webDriver.findElement(By.tagName("h4")).getText());

        }
}


