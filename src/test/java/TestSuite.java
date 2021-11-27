import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"src/test/java/NewPages/GitHub"})
@TestMethodOrder(value = MethodOrderer.MethodName.class)
public class TestSuite {

}
