package NewPages.SonarCloud;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Coverage {

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
    void selectCoverage(){

//        selectMainBranch();
        chromewebDriver.get("https://sonarcloud.io/summary/overall?id=KarimJesusGalvez_spring-patterns");

        WebElement coverageAnchor = chromewebDriver.findElement(By.xpath("//a [contains (@href,'coverage')]"));
        js.executeScript("arguments[0].scrollIntoView();",coverageAnchor);
        coverageAnchor.click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("coverage"));
    }

    @Test
    @DisplayName("Clicks on success filter")
    void seeSuccess(){

//        selectCoverage();
        chromewebDriver.get("https://sonarcloud.io/component_measures?id=KarimJesusGalvez_spring-patterns&metric=coverage&view=list");

        WebElement successAnchor = chromewebDriver.findElement(By.xpath("//a [contains (@data-facet,'test_success_density')]"));
        js.executeScript("arguments[0].scrollIntoView();",successAnchor);
        successAnchor.click();
        assertTrue(chromewebDriver.getCurrentUrl().contains("test_success_density"));
    }
}
