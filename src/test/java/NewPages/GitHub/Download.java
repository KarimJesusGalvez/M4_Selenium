package NewPages.GitHub;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Download Source file from repository
 */
@DisplayName("Download Source file")
public class Download {

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
        action.perform();
        action.keyUp(Keys.CONTROL);
        action.perform();

        chromewebDriver.quit();
    }

    @Test
    @DisplayName("Searches alansastre in the index page")
    void search() {

        WebElement searchbox = chromewebDriver.findElement(By.xpath("//input[@type='text']"));
        searchbox.sendKeys("alansastre");
        searchbox.sendKeys(Keys.ENTER);

        assertTrue(chromewebDriver.getCurrentUrl().contains("https://github.com/search?q=alansastre"));
    }
        @Test
        @DisplayName("Select alansastre")
        void selectUser() {

            chromewebDriver.get("https://github.com/search?q=alansastre");

            WebElement userfilter = chromewebDriver.findElement(By.cssSelector(".menu-item:last-child"));
            userfilter.click();

            // select user, 3 returns img,a,a
            try {
                chromewebDriver.findElements(By.xpath("//a[contains(@href, '/alansastre')]")).get(1).click();
                assertTrue(chromewebDriver.getCurrentUrl().contains("https://github.com/alansastre"));
            } catch (Exception error) {
                error.printStackTrace();
                // TODO add assert Error msg isDisplayed()
                assertTrue(false);
            }

        }

    @Test
    @DisplayName("Select repositories tab")
    void selectRepositories() {

        chromewebDriver.get("https://github.com/alansastre");

        // TODO Fix ElementNOTINTERACTABLE
        chromewebDriver.findElements(By.className("UnderlineNav-item")).get(1).click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("https://github.com/alansastre?tab=repositories"));
    }

    @Test
    @DisplayName("Select proyecto-testing from the list")
    void selectProyecto() {

        chromewebDriver.get("https://github.com/alansastre?tab=repositories");
        // TODO Fix ElementNOTINTERACTABLE Trying to select proyecto-testing
        chromewebDriver.findElements(By.xpath("//a[contains(@href, 'proyecto-testing')]")).get(0).click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("https://github.com/alansastre/proyecto-testing"));
    }

    @Test
    @DisplayName("Select Alicia's fork from the list")
    void selectAliciaFork() {

        chromewebDriver.get("https://github.com/alansastre/proyecto-testing");
        chromewebDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // select stars and forks
        chromewebDriver.findElements(By.cssSelector(".social-count")).get(1).click();

        assertEquals("https://github.com/alansastre/proyecto-testing/network/members", chromewebDriver.getCurrentUrl());

        // select final repo
        chromewebDriver.findElement(By.xpath("//a[contains(@href,'/alicia') and contains(@href,'/proyecto')]")).click();

        assertEquals("https://github.com/alicianunex/proyecto-testing", chromewebDriver.getCurrentUrl());

    }

    @Test
    @DisplayName("Downloads the project's zip file")
    void downloadMaster() throws InterruptedException {

        chromewebDriver.get("https://github.com/alicianunex/proyecto-testing");
        //Select Code button
        chromewebDriver.findElement(By.xpath("//summary[@class='btn-primary btn']")).click();

        WebElement giturl = chromewebDriver.findElements(By.xpath("//input[contains(@class, 'form-control') and contains(@aria-label,'alicia')]")).get(0);
        assertEquals("https://github.com/alicianunex/proyecto-testing.git",giturl.getAttribute("aria-label"));

        // Select download button
        WebElement dowloadbtn = chromewebDriver.findElement(By.xpath("//a[contains(@href,'zip')]"));
        dowloadbtn.click();

        Thread.sleep(60);

        // TODO wait until download is finished, use debugger mode to download
/*
        new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.d(By.cssSelector("UnderlineNav-item")));
*/
    }

    @Test
    @DisplayName("Selects the karim branch")
    void selectBranchKarim() {

        chromewebDriver.get("https://github.com/alicianunex/proyecto-testing");

        WebElement branchbtn = chromewebDriver.findElement(By.xpath("//summary[@class='btn css-truncate']"));
        branchbtn.click();

        WebElement inputbranch = chromewebDriver.findElement(By.cssSelector("#context-commitish-filter-field"));
        inputbranch.sendKeys("karim");

        WebElement brachanchor = chromewebDriver.findElement(By.xpath("//a[@class='SelectMenu-item']"));
        brachanchor.click();

        assertEquals("https://github.com/alicianunex/proyecto-testing/tree/karim_jesus",chromewebDriver.getCurrentUrl());
    }


    @Test
    @DisplayName("Downloads the branch's zip file")
    void downloadkarim() throws InterruptedException {

        chromewebDriver.get("https://github.com/alicianunex/proyecto-testing/tree/karim_jesus");
        //Select Code button
        chromewebDriver.findElement(By.xpath("//summary[@class='btn-primary btn']")).click();

        WebElement giturl = chromewebDriver.findElements(By.xpath("//input[contains(@class, 'form-control') and contains(@aria-label,'alicia')]")).get(0);
        assertEquals("https://github.com/alicianunex/proyecto-testing.git", giturl.getAttribute("aria-label"));

        // Select download button
        WebElement dowloadbtn = chromewebDriver.findElement(By.xpath("//a[contains(@href,'zip')]"));
        dowloadbtn.click();

//        Thread.sleep(500);

        // TODO wait until download is finished, use debug to download
/*
        new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.d(By.cssSelector("UnderlineNav-item")));
*/
    }


    @Nested
    @DisplayName("Downloads this repository's zip")
    public class m4download {

        @Test
        @DisplayName("Selects m4 repo")
        void selectM4repo() {

            chromewebDriver.manage().window().maximize();
            WebElement searchbox = chromewebDriver.findElement(By.xpath("//input[@type='text']"));
            searchbox.click();
            searchbox.sendKeys("m4_selenium");
            searchbox.sendKeys(Keys.ENTER);

            assertTrue(chromewebDriver.getCurrentUrl().contains("github.com/search?q=m4_selenium"));

            List<WebElement> repoanchors = chromewebDriver.findElements(By.xpath("//ul[contains(@class,'repo-list')]//a"));
            repoanchors.get(0).click();
            assertTrue(chromewebDriver.getCurrentUrl().contains("github.com/KarimJesusGalvez/M4_Selenium"));
        }


        @Test
        @DisplayName("Downloads the branch's zip file")
        void downloadKmaster() throws InterruptedException {

            chromewebDriver.get("https://github.com/KarimJesusGalvez/M4_Selenium");
            //Select Code button
            chromewebDriver.findElement(By.xpath("//summary[@class='btn-primary btn']")).click();

            WebElement giturl = chromewebDriver.findElements(By.xpath("//input[contains(@class, 'form-control') and contains(@aria-label,'KarimJesus')]")).get(0);
            assertEquals("https://github.com/KarimJesusGalvez/M4_Selenium.git", giturl.getAttribute("aria-label"));

            // Select download button
            WebElement dowloadbtn = chromewebDriver.findElement(By.xpath("//a[contains(@href,'zip')]"));
            dowloadbtn.click();

//        Thread.sleep(500);

            // TODO wait until download is finished, use debug to download
/*
        new WebDriverWait(chromewebDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.d(By.cssSelector("UnderlineNav-item")));
*/
        }
    }
}

