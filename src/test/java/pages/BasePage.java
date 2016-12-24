package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.WebDriverSetup;

/**
 * Created by User on 11.12.2016.
 */
public class BasePage extends WebDriverSetup{
    private Wait<WebDriver> wait = new WebDriverWait(getDriver(), 15).ignoring(StaleElementReferenceException.class);


    public void waitFor(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public BasePage(){
        PageFactory.initElements(WebDriverSetup.getDriver(), this);
    }
}
