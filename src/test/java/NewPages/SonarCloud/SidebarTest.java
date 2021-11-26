package NewPages.SonarCloud;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class SidebarTest {

    //https://sonarcloud.io/project/overview?id=KarimJesusGalvez_spring-patterns
    WebDriver chromewebDriver;
    JavascriptExecutor js;

    @BeforeEach
    void setUp() {

//        String dir = System.getProperty("user.dir");
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
    void tearDown() {
        chromewebDriver.quit();
    }

    @Test
    @DisplayName("The project name is displayed correctly")
    void checkProjectName (){

        WebElement titlename = chromewebDriver.findElement(By.xpath(" //strong[@class='medium']"));
        int startingindex = (chromewebDriver.getCurrentUrl().length())-(titlename.getText().length());
        String titleURL = chromewebDriver.getCurrentUrl().substring(startingindex);
        assertEquals(titlename.getText(),titleURL);
    }
    @Test
    void checkProjectInitial (){

        WebElement sidebar = chromewebDriver.findElement(By.xpath("//div [contains(@class,'nav-sidebar')]"));
        WebElement titleinitial = sidebar.findElement(By.xpath("//div [text()='S' and contains(@class,'css')]"));
        assertEquals("S",titleinitial.getText());
    }

    @Test
    @DisplayName("Selects the Main branch")
    void selectMainBranch() {

        WebElement mainBranchAnchor = chromewebDriver.findElement(By.xpath("//a [@class='nav-sidebar-item' and contains(@href,'summary')]"));
        js.executeScript("arguments[0].scrollIntoView();", mainBranchAnchor);
        mainBranchAnchor.click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("summary"));
    }

    @Test
    @DisplayName("Goes to the index page")
    void selectOverview() {

        WebElement summaryAnchor = chromewebDriver.findElement(By.xpath("//a [contains(@class,'nav-sidebar-item') and contains(@href,'overview')]"));
        js.executeScript("arguments[0].scrollIntoView();", summaryAnchor);
        summaryAnchor.click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("sonarcloud.io/project/overview"));
    }

    @Test
    @DisplayName("Selects the pull request menu")
    void selectPullRequest() {

        WebElement pullReqAnchor = chromewebDriver.findElement(By.xpath("//div/a [@class='nav-sidebar-item' and contains(@href,'pull_request')]"));
        js.executeScript("arguments[0].scrollIntoView();", pullReqAnchor);
        pullReqAnchor.click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("sonarcloud.io/project/pull_requests_list"));
    }

    @Test
    void selectBranches() {

        WebElement branchesAnchor = chromewebDriver.findElement(By.xpath("//a [@class='nav-sidebar-item' and contains(@href,'branches_list')]"));
        js.executeScript("arguments[0].scrollIntoView();", branchesAnchor);
        branchesAnchor.click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("sonarcloud.io/project/branches_list"));
    }

    @Test
    void selectInformationMenu() {

        WebElement informationAnchor = chromewebDriver.findElement(By.xpath("//a [@class='nav-sidebar-item' and contains(@href,'information')]"));
        js.executeScript("arguments[0].scrollIntoView();", informationAnchor);
        informationAnchor.click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("sonarcloud.io/project/information"));
    }

    @Test
    void selectCollapse() {

        WebElement sidebar = chromewebDriver.findElement(By.xpath("//div[contains(@class,'nav-sidebar')]"));
        Dimension initialsize = sidebar.getSize();
        WebElement collapsebtn = chromewebDriver.findElement(By.cssSelector("#footer"));
        js.executeScript("arguments[0].scrollIntoView();", collapsebtn);
        collapsebtn.click();
        sidebar = chromewebDriver.findElement(By.xpath("//div[contains(@class,'nav-sidebar')]"));
        Dimension finalsize = sidebar.getSize();
        assertTrue(initialsize != finalsize);
        collapsebtn.click();
    }

    @Nested
    @Disabled("Needs identification")
    public class AdministrationMenu {

        @Test
        void clickAdministrationMenu() {

            WebElement administrationButton = chromewebDriver.findElements(By.xpath("//a [@href='#' and @type='button']")).get(0);
            js.executeScript("arguments[0].scrollIntoView();", administrationButton);
            Actions action = new Actions(chromewebDriver);
            action.moveToElement(administrationButton).perform();
            WebElement submenu = chromewebDriver.findElement(By.xpath("//div[@class='popup-portal']"));
            assertTrue(submenu.isDisplayed());
        }

        @Test
        void checkMenuAnchors() {

            WebElement AdministrationButton = chromewebDriver.findElements(By.xpath("//a [@href='#' and @type='button']")).get(0);
            js.executeScript("arguments[0].scrollIntoView();", AdministrationButton);
            Actions action = new Actions(chromewebDriver);
            action.click(AdministrationButton).perform();
            WebElement submenu = chromewebDriver.findElement(By.xpath("//div[@class='popup-portal']"));
            List<WebElement> anchorList = submenu.findElements(By.xpath("//li/a"));

            // TODO spit into method for each entry

            for (WebElement element : anchorList) {
                String name = element.getAttribute("Name");
                assertNotNull(name);
                action.click(element).perform();
            }
        }
    }

}
