package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import pages.PhonesPage;
import pages.ProductCartPage;
import pages.SmartphonesPage;
import pages.StartPage;
import setup.WebDriverSetup;

import java.io.IOException;

/**
 * Created by User on 11.12.2016.
 */
public class RozetkaTestSteps extends WebDriverSetup {
    StartPage startPage;
    PhonesPage phonesPage;
    SmartphonesPage smartphonesPage;
    ProductCartPage productCartPage;

    private String expectedSortingText = "от дешевых к дорогим";

    @Given("I open url \"$link\"")
    public void iOpenUrl(String link){
        openUrl(link);
    }

    @Given("I open smartphones section")
    public void iOpenSmartphonesSection(){
        startPage = new StartPage();
        startPage.clickPhoneTVSection();
        phonesPage = startPage.clickPhonesSection();
        smartphonesPage = phonesPage.clickSmartphonesLink();
    }

    @Given("I open affordable smartphones section")
    public void openAffordableSmartphones(){
        smartphonesPage.clickObtainableSmartphonesLink();
    }

    @Given("I sort affordable smartphones by price ascending")
    public void sortSmartphonesByPrice(){
        smartphonesPage.clickSortingDropdown();
        smartphonesPage.chooseAscendingPriceSorting();
        Assert.assertEquals("text is not equal to expected", expectedSortingText, smartphonesPage.getSortingDropdownText());
//        Assert.assertEquals("text is not equal to expected", smartphonesPage.getSortingOptionText(), smartphonesPage.getSortingDropdownText());
    }

    @Given("I save data for 3 devices from 3 pages into file")
    public void saveDataIntoFile(){
        smartphonesPage.saveDataIntoFile();
    }

    @When("I click buy button for first device")
    public void clickBuyButtonForFirstDevice(){
        productCartPage = smartphonesPage.clickFirstDeviceBuyButton();
    }

    @Then("recommendations block should contain text \"$expectedText\"")
    public void recommendationBlockShouldContainText(String $expectedText){
        String actualText = productCartPage.getRecommendationBlockText();
        Assert.assertTrue("Failed assert that actual text contains expected. Expected: '" + $expectedText +"' Actual: '" + actualText +"'",
                actualText.contains($expectedText));
    }

    @Given("I save data for 3 devices from 3 pages into databse")
    public void saveDataIntoDatabase(){
        smartphonesPage.setAllDevicesDataToDB();
    }
}
