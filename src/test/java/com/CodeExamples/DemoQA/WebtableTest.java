package com.CodeExamples.DemoQA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static com.CodeExamples.DemoQA.SendKeysTestExampleTest.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebtableTest {


    WebDriver webDriver;
    final String targeturl = "https://demoqa.com/webtables";

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
    void submitEmptyFormTest() {
        assertEquals(3, webDriver.findElements(By.className("action-buttons")).size());


        // 3 ways of retrieving
        List<WebElement> deleteButtons = webDriver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
        List<WebElement> deleteButtonsv2 = webDriver.findElements(By.xpath("//span[@title='Delete')]"));
        List<WebElement> deleteButtonsv3 = webDriver.findElements(By.cssSelector("//span[id*='delete-record-')]"));

        //deleteButtons.get(0).click();
        //assertEquals(3, webDriver.findElements(By.className("action-buttons")).size());

        //deleteButtons = webDriver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
        //deleteButtons.get(0).click();

        for (int count = deleteButtons.size(); count > 0; count--) {
            List<WebElement> deleteButtonsfor = webDriver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
            deleteButtonsfor.get(0).click();
            assertEquals(count - 1, webDriver.findElements(By.className("action-buttons")).size());
        }

        int whilecount = webDriver.findElements(By.className("action-buttons")).size();
        do {
            deleteButtons.get(0).click();
            assertEquals(whilecount - 1, webDriver.findElements(By.className("action-buttons")).size());
            deleteButtons = webDriver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
        } while (deleteButtons.size() != 0);

    }

    @Test
    void searchBoxTest() {

        assertEquals(3, webDriver.findElements(By.className("action-buttons")).size());

        WebElement searchbox = webDriver.findElement(By.id("searchBox"));
        searchbox.sendKeys("Legal");
        assertEquals(1, webDriver.findElements(By.className("action-buttons")).size());
    }
    @Test
    void searchBoxNotFoundTest() {

        assertEquals(3, webDriver.findElements(By.className("action-buttons")).size());

        WebElement searchbox = webDriver.findElement(By.id("searchBox"));
        searchbox.sendKeys("fdsffs");
        assertEquals(0, webDriver.findElements(By.className("action-buttons")).size());

    }
    @Test
    void AddButtonTest() {

        webDriver.findElement(By.id("addNewRecordButton")).click();

        assertTrue(webDriver.findElement(By.className("modal-content")).isDisplayed());

        WebElement submitbutton = webDriver.findElement(By.id("submit"));
        //js.executeScript("arguments[0].scrollIntoView();", submitbutton);
        submitbutton.click();

    }

    @Test
    void AddExcessWordsFirstNameTest() {

        webDriver.findElement(By.id("addNewRecordButton")).click();

        webDriver.findElement(By.id("firstName")).sendKeys("qwertyuiopqwertyuiopqwertyuiop"); // 30letters
        // Should crop to 25 chars
        assertEquals(25,webDriver.findElement(By.id("firstName")).getAttribute("value").length());
        assertEquals("qwertyuiopqwertyuiopqwert",webDriver.findElement(By.id("firstName")).getAttribute("value"));

    }

    @Test
    void AddRegOKTest() {

        assertEquals(3, webDriver.findElements(By.className("action-buttons")).size());

        webDriver.findElement(By.id("addNewRecordButton")).click();
        //js.executeScript("arguments[0].scrollIntoView();", submitbutton);


        webDriver.findElement(By.id("firstName")).sendKeys("Name1");
        webDriver.findElement(By.id("lastName")).sendKeys("lastName1");
        webDriver.findElement(By.id("userEmail")).sendKeys("valid@test.com");
        webDriver.findElement(By.id("age")).sendKeys("44");
        webDriver.findElement(By.id("salary")).sendKeys("4466");
        webDriver.findElement(By.id("department")).sendKeys("dep1");

        WebElement submitbutton = webDriver.findElement(By.id("submit"));
        //js.executeScript("arguments[0].scrollIntoView();", submitbutton);
        submitbutton.click();

        assertEquals(4, webDriver.findElements(By.className("action-buttons")).size());
    }

    @Test
    void  editRowTest(){
        List<WebElement> editbutton = webDriver.findElements(By.xpath("//span[contains(@id, 'edit-record-')]"));
        editbutton.get(0).click();

        assertTrue((webDriver.findElement(By.className("modal-content")).isDisplayed()));


    }

    @Test
void closePopup() throws InterruptedException{
        webDriver.findElement(By.id("addNewRecordButton")).click();
        assertTrue((webDriver.findElement(By.className("modal-content")).isDisplayed()));
        webDriver.findElement(By.className("close")).click();
        sleep();
    assertEquals(0, webDriver.findElements(By.className("modal-content")).size());


    }

    @Test
    void paginationTest(){

       WebElement webElement = webDriver.findElement(By.cssSelector(".pagination-bottom select>.optio"));
       webElement.click();

    }

}
