package NewPages.SonarCloud;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TopBar {
    //https://sonarcloud.io/project/overview?id=KarimJesusGalvez_spring-patterns
    WebDriver chromewebDriver;
    JavascriptExecutor js;

    @BeforeEach
    void setUp() {
//
//        String dir = System.getProperty("user.dir");
//
////        String driverUrl = "C:\\data\\chromedriver.exe";
////        System.setProperty("webdriver.chrome.driver",driverUrl);
//        Path path = Paths.get("C:\\data\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver",path.toString());
//        chromewebDriver = new ChromeDriver();

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
        chromewebDriver.findElement(By.xpath("//ul[@class='menu']/li/a")).click();
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

}
