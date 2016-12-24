package setup;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by User on 11.12.2016.
 */
public class WebDriverSetup {
    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    @BeforeScenario
    public static void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterScenario
    public static void tearDown(){
        driver.close();
    }

    public static void openUrl(String url){
        driver.get(url);
    }
}
