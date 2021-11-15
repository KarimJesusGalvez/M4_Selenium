package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChromeExampleTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        String driverURL = "D:\\Nueva carpeta\\Curriculum\\Adecco\\Curso Testing Java\\Temario\\Modulo_4_Selenium\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverURL);
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {driver.quit();}

    @Test
    void test1(){
        // https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/

        driver.get("https://www.selenium.dev/es/documentation/getting_started/installing_browser_drivers/");
        String title = driver.getTitle();
        driver.close();
        assertEquals("Installing browser drivers | Selenium",title);
    }

    @Test
    void close(){driver.close();}
}
