package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.DBHelper;
import setup.PropertiesReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 11.12.2016.
 */
public class SmartphonesPage extends BasePage {
    public SmartphonesPage() {
        PageFactory.initElements(BasePage.getDriver(), this);
    }

    private PropertiesReader propertiesReader = new PropertiesReader();
    private String expectedSortingOptionText;
    private String filePath;
    private String dbTableName;
    private static int id = 0;

//    public String getSortingOptionText(){
//        try {
//            expectedSortingOptionText = propertiesReader.getProperty("expectedSortingOptionTextProperty");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return expectedSortingOptionText;
//    }

    public String getFilePath(){
        try {
            filePath = propertiesReader.getProperty("filePath");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public String getDBTableName(){
        try {
            dbTableName = propertiesReader.getProperty("dbTableName");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbTableName;
    }

    @FindBy(xpath = "//i[.='Доступные смартфоны']//parent::span[contains(@class, 'parametrs')]")
    WebElement obtainableSmartphonesLink;

    @FindBy(xpath = "//a[@name='drop_link']")
    WebElement sortingDropdown;

    @FindBy(xpath = "//a[.='от дешевых к дорогим']")
    WebElement priceAscendingLink;

    @FindBy(xpath = "//li[@id='page1']")
    WebElement firstLinkList;

    @FindBy(xpath = "//li[@id='page2']")
    WebElement secondListLink;

    @FindBy(xpath = "//li[@id='page3']")
    WebElement thirdListLink;

    @FindBy(xpath = "//div[@name='filter_parameters_title'][contains(text(), 'Класс')]")
    WebElement expandClassBlock;

    @FindBy(xpath = "//li[contains(@class, 'active')]/span")
    WebElement currentPageNumberBlock;

    @FindBy(xpath = "(//div[@class='over-wraper']//button[@name='topurchasesfromcatalog'])[1]")
    WebElement firstDeviceBuyButton;

    @FindAll({
            @FindBy(xpath = "//div[contains(@class, 'tile-catalog')]//div[contains(@class, 'tile-catalog')][1]//div[contains(@class, 'g-price-uah')]"),
            @FindBy(xpath = "//div[contains(@class, 'tile-catalog')]//div[contains(@class, 'tile-catalog')][2]//div[contains(@class, 'g-price-uah')]"),
            @FindBy(xpath = "//div[contains(@class, 'tile-catalog')]//div[contains(@class, 'tile-catalog')][3]//div[contains(@class, 'g-price-uah')]")
    })
    List<WebElement> devicePrice;

    @FindAll({
            @FindBy(xpath = "//div[contains(@class, 'tile-catalog')]//div[contains(@class, 'tile-catalog')][1]//div[contains(@class, 'g-i-tile-i-title')]"),
            @FindBy(xpath = "//div[contains(@class, 'tile-catalog')]//div[contains(@class, 'tile-catalog')][2]//div[contains(@class, 'g-i-tile-i-title')]"),
            @FindBy(xpath = "//div[contains(@class, 'tile-catalog')]//div[contains(@class, 'tile-catalog')][3]//div[contains(@class, 'g-i-tile-i-title')]")
    })
    List<WebElement> deviceName;

    public void clickObtainableSmartphonesLink() {
        this.waitFor(obtainableSmartphonesLink);
        obtainableSmartphonesLink.click();
    }

    public void expandClassBlock() {
        this.waitFor(expandClassBlock);
        expandClassBlock.click();
    }

    public void clickSortingDropdown() {
        this.waitFor(sortingDropdown);
        sortingDropdown.click();
    }

    public void chooseAscendingPriceSorting() {
        this.waitFor(priceAscendingLink);
        priceAscendingLink.click();
    }

    public String getSortingDropdownText() {
        this.waitFor(sortingDropdown);
        return sortingDropdown.getText();
    }

    public void openFirstList(){
        this.waitFor(firstLinkList);
        firstLinkList.click();
    }

    public void openSecondList() {
        this.waitFor(secondListLink);
        secondListLink.click();
    }

    public void openThirdList() {
        this.waitFor(thirdListLink);
        thirdListLink.click();
    }

    public String getCurrentPageNumber() {
        this.waitFor(currentPageNumberBlock);
        return currentPageNumberBlock.getText();
    }

    public ProductCartPage clickFirstDeviceBuyButton(){
        this.openFirstList();
        this.waitFor(firstDeviceBuyButton);
        firstDeviceBuyButton.click();
        return new ProductCartPage();
    }

    public String getDevicesDataForList() {
        String data;
        String[] arrayData = new String[3];
        data = this.getCurrentPageNumber() + " страница";
        for (int i = 0; i < 3; i++) {
            arrayData[i] = "Название: " + deviceName.get(i).getText() + " Цена: " + devicePrice.get(i).getText();
            int number = i + 1;
            data = data + "\n" + number + " устройство: " + arrayData[i];
        }
        return data + "\n\n";
    }

    public String getDevicesDataForThreeLists(){
        String allData = "";
        this.openFirstList();
        allData = allData + this.getDevicesDataForList();
        this.openSecondList();
        allData = allData + this.getDevicesDataForList();
        this.openThirdList();
        allData = allData + this.getDevicesDataForList();
        return allData;
    }

    public void saveDataIntoFile(){
        String textToSave = this.getDevicesDataForThreeLists();
        try {
            String fileAbsolutePath;
            File newFile = new File(this.getFilePath());
            newFile.createNewFile();
            FileWriter fileWriter = new FileWriter(this.getFilePath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textToSave);
            bufferedWriter.close();
            fileAbsolutePath = newFile.getAbsolutePath();
            System.out.println("File was saved at: " + fileAbsolutePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDevicesDataToDB(String tableName){
        String idString;
        String name;
        String price;
        for (int i = 0; i < 3; i++){
            id++;
            idString = Integer.toString(id);
            name = deviceName.get(i).getText();
            price = devicePrice.get(i).getText();

            DBHelper dbHelper = new DBHelper();
            dbHelper.setDBTableData(idString, name, price, tableName);
        }
    }

    public void setAllDevicesDataToDB(){
        DBHelper dbHelper = new DBHelper();
        dbHelper.createTable(this.getDBTableName());
        this.openFirstList();
        this.setDevicesDataToDB(this.getDBTableName());
        this.openSecondList();
        this.setDevicesDataToDB(this.getDBTableName());
        this.openThirdList();
        this.setDevicesDataToDB(this.getDBTableName());

        System.out.println(dbHelper.getDBCreatedPhoneData(this.getDBTableName()));
    }
}
