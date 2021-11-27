package NewPages.SonarCloud;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TopBar {
    //https://sonarcloud.io/project/overview?id=KarimJesusGalvez_spring-patterns
    WebDriver chromewebDriver;
    JavascriptExecutor js;

    @BeforeEach
    void setUp() {
//
        String dir = System.getProperty("user.dir");

//        String driverUrl = "C:\\data\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver",driverUrl);
        Path path = Paths.get("C:\\data\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",path.toString());
        chromewebDriver = new ChromeDriver();

        // TODO phantom breaks tests use extra waits ??
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--headless");
//        chromewebDriver = new ChromeDriver(options);
//        chromewebDriver.manage().window().maximize();

        js = (JavascriptExecutor) chromewebDriver;
        chromewebDriver.get("https://sonarcloud.io/project/overview?id=KarimJesusGalvez_spring-patterns");
        chromewebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    void tearDown() {
        chromewebDriver.quit();
    }

    @Test
    void checkSearchBar() {

        WebElement navbar = chromewebDriver.findElement(By.xpath("//div[contains(@class,'navbar-content')]"));
        WebElement searchbutton = navbar.findElements(By.xpath("//nav//li/button")).get(0);
        searchbutton.click();

        WebElement searchinput = chromewebDriver.findElement(By.xpath("//input"));
        Actions action = new Actions(chromewebDriver);
        action.sendKeys(searchinput,"proyecto-testing").perform();
        // select the first result

        new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='menu']/li/a")));
        chromewebDriver.findElement(By.xpath("//ul[@class='menu']/li/a")).click();
        new WebDriverWait(chromewebDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("proyecto-testing"));
        assertTrue(chromewebDriver.getCurrentUrl().contains("proyecto-testing"));
    }
 @Test
    void checkIconimg() {

        assertDoesNotThrow(()->chromewebDriver.findElement(By.xpath("//img[@alt='Continuous Code Quality']")));
    }

    @Test
    void checkIcons() {

        // TODO assert paths of icons??
        WebElement navbar = chromewebDriver.findElement(By.xpath("//div[contains(@class,'navbar-content')]"));
        assertTrue(navbar.findElements(By.xpath("//nav//li/button")).size() >= 2);
    }


    @Test
    @DisplayName("Checks the log in anchor")
    void checkLogIn() {

        chromewebDriver.manage().window().maximize();
        WebElement login = chromewebDriver.findElement(By.xpath("//a[contains(@class,'navbar-login')]"));
        js.executeScript("arguments[0].scrollIntoView();", login);
        new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(login));
        Actions action = new Actions(chromewebDriver);
        action.click(login).perform();
        assertTrue(chromewebDriver.getCurrentUrl().contains("sonarcloud.io/sessions"));
    }

    @Nested
    public class HelpMenu {

        @Test
        @DisplayName("Opens the help menu correctly")
        void checkHelpbutton() {

            WebElement helpbtn = chromewebDriver.findElement(By.xpath("//button[contains(@title,'Help')]"));
            Actions action = new Actions(chromewebDriver);
            new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(helpbtn));
            action.click(helpbtn).perform();
            WebElement helpmenu = chromewebDriver.findElement(By.xpath("//ul[contains(@class,'HelpMenu')]"));
            assertTrue(helpmenu.isDisplayed());
        }

        @Test
        @DisplayName("Checks the Documentation link")
        void checkDocumentationAnchor() {

            checkHelpbutton();

            WebElement documentation = chromewebDriver.findElement(By.xpath("//a[text()='Documentation']"));
            Actions action = new Actions(chromewebDriver);
            new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(documentation));
            action.click(documentation).perform();

            ArrayList<String> tabs = new ArrayList<>(chromewebDriver.getWindowHandles());
            String handleName = tabs.get(1);
            chromewebDriver.switchTo().window(handleName);
            System.setProperty("current.window.handle", handleName);

            new WebDriverWait(chromewebDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("documentation"));
            assertTrue(chromewebDriver.getCurrentUrl().contains("https://sonarcloud.io/documentation"));
        }

        @Test
        @DisplayName("Checks the Community link")
        void checkCommunityAnchor() {

            checkHelpbutton();

            WebElement community = chromewebDriver.findElement(By.xpath("//a[text()='Community Forum']"));
            Actions action = new Actions(chromewebDriver);
            new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(community));
            action.click(community).perform();

            ArrayList<String> tabs = new ArrayList<>(chromewebDriver.getWindowHandles());
            String handleName = tabs.get(1);
            chromewebDriver.switchTo().window(handleName);
            System.setProperty("current.window.handle", handleName);

            new WebDriverWait(chromewebDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("community"));
            assertTrue(chromewebDriver.getCurrentUrl().contains("community.sonarsource.com"));
        }

        @Test
        @DisplayName("Checks the Api link")
        void checkAPIAnchor() {

            checkHelpbutton();

            WebElement api = chromewebDriver.findElement(By.xpath("//a[text()='Web API']"));
            Actions action = new Actions(chromewebDriver);
            new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(api));
            action.click(api).perform();

//            ArrayList<String> tabs = new ArrayList<>(chromewebDriver.getWindowHandles());
//            String handleName = tabs.get(1);
//            chromewebDriver.switchTo().window(handleName);
//            System.setProperty("current.window.handle", handleName);

            new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains("web_api"));
            assertTrue(chromewebDriver.getCurrentUrl().contains("https://sonarcloud.io/web_api"));
        }
    }
}
