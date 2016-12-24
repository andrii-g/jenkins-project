package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.WebDriverSetup;

/**
 * Created by User on 11.12.2016.
 */
public class StartPage extends BasePage{

    public StartPage(){
        PageFactory.initElements(WebDriverSetup.getDriver(), this);
    }

    @FindBy(xpath = "//a[@data-title='Телефоны, ТВ и электроника']")
    WebElement phonesTvLink;

    @FindBy (xpath = "//a[.='Телефоны']")
    WebElement phonesLink;

    public void clickPhoneTVSection() {
        this.waitFor(phonesTvLink);
        phonesTvLink.click();
    }

    public PhonesPage clickPhonesSection() {
        this.waitFor(phonesLink);
        phonesLink.click();
        return new PhonesPage();
    }
}
