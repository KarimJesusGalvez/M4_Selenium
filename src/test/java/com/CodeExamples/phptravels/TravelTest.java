package com.CodeExamples.phptravels;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelTest {

    private static final String URL = "https://www.phptravels.net/";
    WebDriver driver;
    JavascriptExecutor js;


    @BeforeEach
    void setUp() {
        String dir = System.getProperty("user.dir"); // ruta del proyecto
        String driverUrl = "/drivers/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);
        driver = new ChromeDriver(); // Google Chrome
        js = (JavascriptExecutor) driver;
        driver.get(URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void travelTest() throws InterruptedException {


        driver.findElement(By.id("select2-hotels_city-container")).click();

        driver.findElement(By.className("select2-search__field")).sendKeys("Dubai");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='select2-hotels_city-results']//li[text()='Dubai,United Arab Emirates']")));

        // Thread.sleep(5000);

        //System.out.println(driver.findElement(By.cssSelector("#select2-hotels_city-results li")).getAttribute("innerHTML"));
        driver.findElement(By.cssSelector("#select2-hotels_city-results li")).click();

        driver.findElement(By.id("submit")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("data")));

        // hotels_amenities_ Hay 6 hoteles
        // By.className("hotels_amenities_")
        // By.cssSelector(".hotels_amenities_")
        List<WebElement> hotels = driver.findElements(By.className("hotels_amenities_"));
        assertEquals(6, hotels.size());

    }

    @Test
    void fiveStarFilter() {
        // testear filtro 5 estrellas

        WebElement searchlocation = driver.findElement(By.id("select2-hotels_city-container"));
        searchlocation.click();

        searchlocation = driver.findElement(By.className("select2-search__field"));
        searchlocation.sendKeys("Dubai");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='select2-hotels_city-results']//li[text()='Dubai,United Arab Emirates']")));

        //driver.findElement(By.cssSelector("#select2-hotels_city-results li")).click();
        driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li")).click();

        WebElement buttons = driver.findElement(By.xpath("/html/body/section[1]/div/div/div/div/div[2]/div/div[1]/form/div/div/div[4]/div/button"));
        buttons.sendKeys(Keys.ENTER);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("markers")));

        List<WebElement> results = driver.findElements(By.cssSelector("#markers section ul li"));
        assertTrue(results.size() >= 6);

        driver.findElement(By.xpath("//label[@for='stars_5']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.attributeContains
                        (driver.findElements(By.xpath("//div[@id='markers']/section/ul/li")).get(0)
                        ,"style","display: none;"));


        results = driver.findElements(By.xpath("//div[@id='markers']/section/ul/ li[@style='display: none;']"));
        assertEquals(5,results.size());
    }

    @Test
    void detailsButton() {
        // testear pantalla Details: pulsar en el primer hotel en el botón Details y comprobar la pantalla que aparece, que sea de Dubai
        // TODO

        // call last page
       // xpath
        //href="https://www.phptravels.net/en/usd/hotel/dubai/jumeirah-beach-hotel/32/11-11-2021/12-11-2021/1/2/0/1//0/0/" class="more_details effect mt-0 btn-block ladda-button waves-effect" data-style="zoom-in"><span class="ladda-label">
//        click
        // assert //.tour-detail-area
        // contains Dubai
    }

}

