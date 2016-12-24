package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 11.12.2016.
 */
public class ProductCartPage extends BasePage {

    public ProductCartPage() {
        PageFactory.initElements(BasePage.getDriver(), this);
    }

    @FindBy(xpath = "//div[@id='cart-popup']//h3[contains(text(), 'Вместе дешевле')]")
    WebElement recommendationBlock;

//    @FindBy(xpath = "//div[@id='detail_buy_label']//span[contains(@class, 'btn-link-green')]//button[@name='topurchases']")
//    WebElement buyButton;

    public String getRecommendationBlockText() {
        this.waitFor(recommendationBlock);
        return recommendationBlock.getText();
    }

//    public void clickBuyButton() {
//        this.waitFor(buyButton);
//        buyButton.click();
//    }
}
