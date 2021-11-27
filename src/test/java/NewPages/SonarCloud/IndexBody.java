package NewPages.SonarCloud;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class IndexBody {

    //https://sonarcloud.io/project/overview?id=KarimJesusGalvez_spring-patterns
    WebDriver chromewebDriver;
    JavascriptExecutor js;

    @BeforeEach
    void setUp(){

        String dir = System.getProperty("user.dir");
//
////        String driverUrl = "C:\\data\\chromedriver.exe";
////        System.setProperty("webdriver.chrome.driver",driverUrl);
//        Path path = Paths.get("C:\\data\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver",path.toString());
//        chromewebDriver = new ChromeDriver();
///*
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        chromewebDriver = new ChromeDriver(options);
        chromewebDriver.manage().window().maximize();

        js = (JavascriptExecutor) chromewebDriver;
        chromewebDriver.get("https://sonarcloud.io/project/overview?id=KarimJesusGalvez_spring-patterns");
        chromewebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterEach
    void tearDown() {chromewebDriver.quit();}

    @Nested
    public class Coverage {


        @Test
        @DisplayName("Selects the Main branch")
        void selectMainBranch(){

            WebElement mainBranchAnchor = chromewebDriver.findElement(By.xpath("//a [@class='nav-sidebar-item' and contains(@href,'summary')]"));
            js.executeScript("arguments[0].scrollIntoView();",mainBranchAnchor);
            mainBranchAnchor.click();
            assertTrue(chromewebDriver.getCurrentUrl().contains("summary"));
        }

        @Test
        @DisplayName("Selects Coverage menu")
        void selectCoverage() {

//        selectMainBranch();
            chromewebDriver.get("https://sonarcloud.io/summary/overall?id=KarimJesusGalvez_spring-patterns");

            WebElement coverageAnchor = chromewebDriver.findElement(By.xpath("//a [contains (@href,'coverage')]"));
            js.executeScript("arguments[0].scrollIntoView();", coverageAnchor);
            coverageAnchor.click();
            assertTrue(chromewebDriver.getCurrentUrl().contains("coverage"));
        }

        @Test
        @DisplayName("Clicks on test units filter")
        void seeTestunits() {

            chromewebDriver.get("https://sonarcloud.io/component_measures?id=KarimJesusGalvez_spring-patterns&metric=coverage&view=list");
            List<WebElement> columns = chromewebDriver.findElements(By.cssSelector("tbody tr"));
            int initialsize = columns.size();

            WebElement testsanchor = chromewebDriver.findElement(By.xpath("//a [@data-facet='tests']"));
            js.executeScript("arguments[0].scrollIntoView();", testsanchor);
            testsanchor.click();

            new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody tr"),13));

            columns = chromewebDriver.findElements(By.cssSelector("tbody tr"));
            int finalsize = columns.size();
            System.out.println(initialsize + "  " + finalsize);
            assertTrue(initialsize > finalsize);


        }

        @Test
        @DisplayName("Clicks on success filter")
        void seeSuccess() {

//        selectCoverage();
            chromewebDriver.get("https://sonarcloud.io/component_measures?id=KarimJesusGalvez_spring-patterns&metric=coverage&view=list");

            WebElement successAnchor = chromewebDriver.findElement(By.xpath("//a [contains (@data-facet,'test_success_density')]"));
            js.executeScript("arguments[0].scrollIntoView();", successAnchor);
            successAnchor.click();
            assertTrue(chromewebDriver.getCurrentUrl().contains("test_success_density"));
        }
    }

    @Test
    @DisplayName("Checks other Activity button")
    void olderactivitybtn() {

        List<WebElement> activities = chromewebDriver.findElements(By.xpath("//div[contains(@class,'Activity')]"));
        int initialsize = activities.size();
        WebElement btn = chromewebDriver.findElement(By.xpath("//button[text()='Show Older Activity']"));
        btn.click();

        new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class,'Activity')]"),16));

        activities = chromewebDriver.findElements(By.xpath("//div[contains(@class,'Activity')]"));
        int finalsize = activities.size();
        assertNotEquals(initialsize, finalsize);
    }
}