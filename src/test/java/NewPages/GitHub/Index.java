package NewPages.GitHub;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Index {

    WebDriver chromewebDriver;

    @BeforeEach
    void setUp(){

        String dir = System.getProperty("user.dir");

//        String driverUrl = "C:\\data\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver",driverUrl);
        Path path = Paths.get("C:\\data\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",path.toString());
        chromewebDriver = new ChromeDriver();

        /*
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        chromewebDriver = new ChromeDriver(options);
        chromewebDriver.manage().window().maximize();

*/
        chromewebDriver.get("http://github.com");

        WebElement html = chromewebDriver.findElement(By.tagName("html"));
        Actions action = new Actions(chromewebDriver);
        action.keyDown(Keys.CONTROL);
        action.sendKeys(html,Keys.SUBTRACT);
        action.sendKeys(html,Keys.SUBTRACT);
        action.sendKeys(html,Keys.SUBTRACT);
        action.perform();
        action.keyUp(Keys.CONTROL);
        action.perform();
    }

    @AfterEach
    void tearDown() {
//      firefoxwebDriver.quit();

        WebElement html = chromewebDriver.findElement(By.tagName("html"));
        Actions action = new Actions(chromewebDriver);
        action.keyDown(Keys.CONTROL);
        action.sendKeys(html,Keys.ADD);
        action.sendKeys(html,Keys.ADD);
        action.sendKeys(html,Keys.ADD);
        action.sendKeys(html,Keys.ADD);
        action.perform();
        action.keyUp(Keys.CONTROL);
        action.perform();

        chromewebDriver.quit();
    }

    @Test
    @DisplayName("Checks the title of the page")
    void CheckTitle () {
        WebElement title = chromewebDriver.findElement(By.xpath("//h1"));
        assertEquals("Where the world builds software", title.getText());
        assertEquals("GitHub: Where the world builds software · GitHub", chromewebDriver.getTitle());
    }

    @Test
    @DisplayName("Checks that the canvas is displayed")
    void Checkcanvas () {
        WebElement canvas = chromewebDriver.findElement(By.xpath("//canvas"));
        assertTrue(canvas.isDisplayed());
    }

    @Nested
    public class Header{

        @Test
        @DisplayName("Checks the sing in button")
        public void signIn(){

            chromewebDriver.manage().window().maximize();

            WebElement anchor = chromewebDriver.findElement(By.xpath("//header//a[contains(@href,'login')]"));
            anchor.click();
            assertTrue(chromewebDriver.getCurrentUrl().contains("github.com/login"));
        }

        @Test
        @DisplayName("Checks the sing up button")
        public void signUp(){

            chromewebDriver.manage().window().maximize();
            WebElement header = chromewebDriver.findElement(By.xpath("//header"));
            List<WebElement> anchors = header.findElements(By.xpath("//header//a[contains(@href,'signup')]"));
            anchors.get(1).click();
            assertTrue(chromewebDriver.getCurrentUrl().contains("github.com/signup"));
        }

        @Nested
        @DisplayName("Checks the dropdown menus in the header")
        public class summaries {

            @Test
            @DisplayName("Checks whyGithub? menu")
            public void whyDropdown() {

                List<WebElement> dropdownmenus = chromewebDriver.findElements(By.xpath("//header//summary"));
                Actions action = new Actions(chromewebDriver);
                action.moveToElement(dropdownmenus.get(0)).perform();
                List<WebElement> dropdownanchors = dropdownmenus.get(0).findElements(By.xpath("//a"));
                assertTrue(dropdownanchors.get(0).isDisplayed());
            }

            @Test
            @DisplayName("Checks Feature anchor in the dropdown menu")
            public void featuresAnchor() {

                List<WebElement> dropdownmenus = chromewebDriver.findElements(By.xpath("//header//summary"));
                Actions action = new Actions(chromewebDriver);
                action.moveToElement(dropdownmenus.get(0)).perform();
                WebElement dropdownanchors = dropdownmenus.get(0).findElement(By.xpath("//a[contains(@href,'features')]"));
                assertTrue(dropdownanchors.isDisplayed());

                action.click(dropdownanchors).perform();
                assertEquals("https://github.com/features", chromewebDriver.getCurrentUrl());
            }

            @Test
            void checkFeatureArrow(){

                List<WebElement> dropdownmenus = chromewebDriver.findElements(By.xpath("//header//summary"));
                Actions action = new Actions(chromewebDriver);
                action.moveToElement(dropdownmenus.get(0)).perform();
                WebElement featureanchor = dropdownmenus.get(0).findElement(By.xpath("//a[contains(@href,'features')]"));
                action.moveToElement(featureanchor).perform();
                List<WebElement> arrows = chromewebDriver.findElements(By.xpath("//span[contains(@class,'Bump-link-symbol')]"));
                assertTrue(arrows.get(0).isDisplayed());
            }
        }
    }
}
