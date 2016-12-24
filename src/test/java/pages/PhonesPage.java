package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 11.12.2016.
 */
public class PhonesPage extends BasePage {
    public PhonesPage() {
        PageFactory.initElements(BasePage.getDriver(), this);
    }

    @FindBy(xpath = "//a[.='Смартфоны']")
    WebElement smartphonesLink;

    public SmartphonesPage clickSmartphonesLink() {
        this.waitFor(smartphonesLink);
        smartphonesLink.click();
        return new SmartphonesPage();
    }
}
