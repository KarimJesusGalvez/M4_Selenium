package NewPages.SonarCloud;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Index {


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
    public class Titles {

        @Test
        @DisplayName("Checks the subtitles")
        void CheckSubTitles () {
            List<WebElement> mainBranchAnchor = chromewebDriver.findElements(By.xpath("//h3"));
            List<String> textdata = new ArrayList<>();
            textdata.add("Main Branch Status");
            textdata.add("Main Branch Evolution");
            textdata.add("Latest Activity");

            for (int count = 0; count < mainBranchAnchor.size(); count++) {
                assertEquals(textdata.get(count), mainBranchAnchor.get(count).getText());
            }
        }

        @Test
        @DisplayName("Checks the title of the page")
        void CheckTitle () {
            WebElement title = chromewebDriver.findElement(By.xpath("//h1"));
            assertEquals("spring-patterns", title.getText());
            assertEquals("spring-patterns - KarimJesusGalvez", chromewebDriver.getTitle());
        }

        @Test
        @DisplayName("Checks that the project title is displayed correctly")
        void checkProjectName () {

            WebElement titlename = chromewebDriver.findElement(By.xpath("//h1"));
            int startingindex = (chromewebDriver.getCurrentUrl().length()) - (titlename.getText().length());
            String titleURL = chromewebDriver.getCurrentUrl().substring(startingindex);
            assertEquals(titlename.getText(), titleURL);
        }

        @Test
        @DisplayName("Checks the project Letter symbol is displayed")
        void checkProjectInitial () {

            WebElement sidebar = chromewebDriver.findElement(By.xpath("//div[contains(@class,'js-project')]"));
            sidebar.findElement(By.xpath("//div[text()='S']"));
            assertEquals("S", sidebar.findElement(By.xpath("//div[text()='S']")).getText());
        }
    }

}
