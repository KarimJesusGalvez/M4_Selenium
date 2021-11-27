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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class Login {


    WebDriver chromewebDriver;

    @BeforeEach
    void setUp(){

        String dir = System.getProperty("user.dir");

//        String driverUrl = "C:\\data\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver",driverUrl);
        Path path = Paths.get("C:\\data\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",path.toString());
        chromewebDriver = new ChromeDriver();
//

        // TODO phantom breaks tests
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--headless");
//        chromewebDriver = new ChromeDriver(options);
//        chromewebDriver.manage().window().maximize();


        chromewebDriver.get("http://github.com");

        WebElement html = chromewebDriver.findElement(By.tagName("html"));
        Actions action = new Actions(chromewebDriver);
        action.keyDown(Keys.CONTROL);
        action.sendKeys(html,Keys.SUBTRACT);
        action.sendKeys(html,Keys.SUBTRACT);
        action.sendKeys(html,Keys.SUBTRACT);
        action.sendKeys(html,Keys.SUBTRACT);
        action.perform();
        action.keyUp(Keys.CONTROL);
        action.perform();

        WebElement anchor = chromewebDriver.findElement(By.xpath("//header//a[contains(@href,'login')]"));
        anchor.click();
        assumeTrue(chromewebDriver.getCurrentUrl().contains("github.com/login"));

    }

    @AfterEach
    void tearDown() {
//      firefoxwebDriver.quit();

        WebElement html = chromewebDriver.findElement(By.tagName("html"));
        Actions action = new Actions(chromewebDriver);
        action.keyDown(Keys.CONTROL);
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
        assertEquals("Sign in to GitHub", title.getText());
        assertEquals("Sign in to GitHub · GitHub", chromewebDriver.getTitle());
    }

    @Test
    @DisplayName("Checks the link to index")
    public void indexanchor(){

        WebElement indexbtn = chromewebDriver.findElement(By.xpath("//a[@class='header-logo']"));
        indexbtn.click();
        assertEquals("https://github.com/",chromewebDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Checks the sign up disclaimer")
    public void signup(){

        WebElement signup = chromewebDriver.findElement(By.xpath("//p"));
        assertTrue(signup.getText().contains("New to GitHub?"));

        WebElement createaccount = chromewebDriver.findElement(By.xpath("//a[contains(@href,'login')]"));
        createaccount.click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("github.com/signup?source=login"));

    }

    @Nested
    public class formTest {
        @Test
        @DisplayName("Checks the labels of the inputs")
        void CheckLabels() {
            WebElement form = chromewebDriver.findElement(By.xpath("//form[contains(@action,'/session')]"));
            List<WebElement> labels = form.findElements(By.xpath("//label"));
            assertEquals("Username or email address", labels.get(0).getText());
            assertEquals("Password", labels.get(1).getText());
        }


        @Test
        @DisplayName("Checks the forgot password link ")
        public void forgotPassWordAnchor() {

            WebElement forgotpass = chromewebDriver.findElement(By.xpath("//a[contains(@href,'password_reset')]"));
            forgotpass.click();
            assertTrue(chromewebDriver.getCurrentUrl().contains("github.com/password_reset"));
        }


        @Test
        @DisplayName("Shows error msg when you introduce a wrong user/password pair")
        public void enterWrongUserandPass() {
            WebElement usernameinput = chromewebDriver.findElement(By.xpath("//input[@autocomplete='username']"));
            usernameinput.sendKeys("Exampleuser1");
            WebElement passwordinput = chromewebDriver.findElement(By.xpath("//input[@autocomplete='current-password']"));
            passwordinput.sendKeys("Examplepassword1");

            WebElement savebtn = chromewebDriver.findElement(By.xpath("//input[@type='submit']"));
            savebtn.click();
            WebElement errormsg = chromewebDriver.findElement(By.xpath("//div[contains(@class,'flash-full flash-error')]"));
            assertTrue(errormsg.isDisplayed());
        }

        @Test
        @DisplayName("Shows error msg when you only introduce an user")
        public void enterWrongUser() {
            WebElement usernameinput = chromewebDriver.findElement(By.xpath("//input[@autocomplete='username']"));
            usernameinput.sendKeys("Exampleuser1");

            WebElement savebtn = chromewebDriver.findElement(By.xpath("//input[@type='submit']"));
            savebtn.click();

            new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(chromewebDriver.findElement(By.xpath("//div[contains(@class,'flash-full flash-error')]"))));
            WebElement errormsg = chromewebDriver.findElement(By.xpath("//div[contains(@class,'flash-full flash-error')]"));
            assertTrue(errormsg.isDisplayed());
        }

        @Test
        @DisplayName("Shows error msg when you only introduce a password")
        public void enterWrongPass() {
            WebElement passwordinput = chromewebDriver.findElement(By.xpath("//input[@autocomplete='current-password']"));
            passwordinput.sendKeys("Examplepassword1");

            WebElement savebtn = chromewebDriver.findElement(By.xpath("//input[@type='submit']"));
            savebtn.click();
            WebElement errormsg = chromewebDriver.findElement(By.xpath("//div[contains(@class,'flash-full flash-error')]"));
            assertTrue(errormsg.isDisplayed());
        }
    }

    @Test
    @DisplayName("Checks the anchors of the footer")
    void checkFooterAnchors(){

        List<WebElement> anchors = chromewebDriver.findElements(By.xpath("//div[contains(@class,'footer')]//a"));

        List<String> urls = new ArrayList<>();
        urls.add("https://docs.github.com/en/github/site-policy/github-terms-of-service");
        urls.add("https://docs.github.com/en/github/site-policy/github-privacy-statement");
        urls.add("https://github.com/security");
        urls.add("https://support.github.com/request?tags=dotcom-contact");

        for (int count = 0; count < anchors.size(); count++) {

            List<WebElement> inneranchors = chromewebDriver.findElements(By.xpath("//div[contains(@class,'footer')]//a"));
            inneranchors.get(count).click();
            assertEquals(urls.get(count),chromewebDriver.getCurrentUrl());
            chromewebDriver.get("https://github.com/login");
        }
    }
}
