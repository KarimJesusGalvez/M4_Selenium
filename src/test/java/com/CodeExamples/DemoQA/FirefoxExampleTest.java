package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirefoxExampleTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        String driverURL = "D:\\Nueva carpeta\\Curriculum\\Adecco\\Curso Testing Java\\Temario\\Modulo_4_Selenium\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", driverURL);
        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {driver.quit();}

    @Test
    void test1(){
        // https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/

        driver.get("https://github.com/mozilla/geckodriver");
        String title = driver.getTitle();
        assertEquals("GitHub - mozilla/geckodriver: WebDriver for Firefox",title);
    }
    @Test
    void test2(){
        // https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/

        driver.get("https://github.com/mozilla/geckodriver");
        WebElement h1 = driver.findElement(By.tagName("h1"));
        List<WebElement> h1list = driver.findElements(By.tagName("h1"));
        String h1text = h1.getText();
        assertEquals("Mozilla",h1text);
    }
    @Test
    void classSelector(){
        driver.get("https://github.com/mozilla");

        List<WebElement> repositories = driver.findElements(By.className("Box-row"));
        assertEquals(10, repositories.size());
    }
    @Test
    void close(){driver.close();}








}
