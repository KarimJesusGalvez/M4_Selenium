package com.CodeExamples.OpenMRS;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {

    WebDriver webDriver;
    final String targeturl = "https://demo.openmrs.org/openmrs/login.htm";

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

    public void LoginAdminPharmacy(){

        webDriver.findElement(By.id("username")).sendKeys("Admin");

        webDriver.findElement(By.id("password")).sendKeys("Admin123");
        webDriver.findElement(By.id("Pharmacy")).click();
        webDriver.findElement(By.id("loginButton")).click();


        assertEquals ("Logged in as Super User (admin) at Pharmacy.",
                webDriver.findElement(By.tagName("h4")).getText());


    }
}
