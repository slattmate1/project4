package ru.yandex.praktikum.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SecondOrderPageObject {

    private WebDriver driver;
    //Локатор описания
    private final By descriptionForMan = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Локатор даты доставки
    private final By calendar = By.xpath(".//div[@class='react-datepicker__input-container']/input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    //Локатор даты 29
    private final By day29 = By.xpath(".//div[@class='react-datepicker__day react-datepicker__day--020']");
    //Локатор срока аренды
    private final By arendaField = By.xpath(".//div[@class='Dropdown-placeholder']");
    //Локатор 4 суток в поле аренды
    private final By fourDays = By.xpath(".//div[@class='Dropdown-option' and text()='четверо суток']");
    //Локатор черного цвета
    private final By blackCheckbox = By.xpath(".//input[@id='black']");
    //Локатор кнопки "Заказать"
    private final By buttonOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Локатор для формы подтверждения
    private final By modalWindow = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    //Локатор кнопки подтверждения
    private final By buttonYes = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //Локатор окна оформления заказа
    private final By orderMessage = By.xpath(".//*[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public SecondOrderPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void setDescriptionForMan(String description) {
        driver.findElement(descriptionForMan).sendKeys(description);
    }

    public void clickCalendar() {
        driver.findElement(calendar).click();
    }

    public void choiceDay() {
        driver.findElement(day29).click();
    }

    public void clickArendaField() {
        driver.findElement(arendaField).click();
    }

    public void choiceDayArend() {
        driver.findElement(fourDays).click();
    }

    public void clickBlackChevkbox() {
        driver.findElement(blackCheckbox).click();
    }

    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    public void clickButtonYes() {

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
        driver.findElement(buttonYes).click();

    }

    public String getOrderMessage() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(orderMessage));
        WebElement orderText = driver.findElement(orderMessage);
        return orderText.getText();
    }
}
