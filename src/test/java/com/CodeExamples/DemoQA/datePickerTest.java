package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class datePickerTest {


    WebDriver webDriver;
    final String targeturl = "https://demoqa.com/date-pickers";

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
    void TodayTextTest() {


        WebElement button1 = webDriver.findElement(By.id("datePickerMonthYearInput"));
        LocalDate localdate = LocalDate.now();
        String button1date = button1.getAttribute("class value");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(LocalDate.parse(localdate.toString()));
        System.out.println(LocalDate.parse(button1date.toString()));
        assertEquals(localdate, button1date);
    }

    @Test
    void selectDayTest() {

        WebElement monthselect = webDriver.findElement(By.id("react-datepicker__month-select"));
        monthselect.click();

        WebElement monthoption = webDriver.findElement(By.xpath("//select//option[value='0']"));

        WebElement yearoption = webDriver.findElement(By.xpath("//select//option[value='0']"));

        // check dateString contains 9:00

        // assertTrue(datestring.contains("9:00 AM"))

    }
}
