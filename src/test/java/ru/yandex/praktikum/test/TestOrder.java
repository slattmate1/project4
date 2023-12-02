package ru.yandex.praktikum.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page_object.HomePageObject;
import ru.yandex.praktikum.page_object.OrderPageObject;
import ru.yandex.praktikum.page_object.SecondOrderPageObject;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class TestOrder {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String adres;
    private final String phone;
    private final String comment;
    private final String metro;
    private final String exectedOrderText = "Заказ оформлен";

    public TestOrder(String name, String surname, String adres, String phone, String comment, String metro) {
        this.name = name;
        this.surname = surname;
        this.adres = adres;
        this.phone = phone;
        this.comment = comment;
        this.metro = metro;
    }

    @Parameterized.Parameters()
    public static Object[][] setInfoForOdrer() {
        return new Object[][]{
                {"Наф", "ниф", "Москва", "+79502812000", "Ррр", "Черкизовская"},
                {"Тест", "Тестович", "Иваново", "81231232212", "Тестовой", "Комсомольская"},
        };
    }

    @Before
    public void setup() {
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));



    }

    @Test
    public void choiceOrderWithMiddleButton() {
        HomePageObject homePage = new HomePageObject(driver);
        OrderPageObject pageObject = new OrderPageObject(driver);
        SecondOrderPageObject secondPageObject = new SecondOrderPageObject(driver);
        homePage.open();
        homePage.clickOrderButtonMiddleHmePage();

        pageObject.setNameField(name);
        pageObject.setLastNameField(surname);
        pageObject.setMetroField(metro);
        pageObject.setAdresField(adres);
        pageObject.setNomberPhone(phone);
        pageObject.clickButtonNext();

        secondPageObject.setDescriptionForMan(comment);
        secondPageObject.clickCalendar();
        secondPageObject.choiceDay();
        secondPageObject.clickArendaField();
        secondPageObject.choiceDayArend();
        secondPageObject.clickBlackChevkbox();
        secondPageObject.clickButtonOrder();
        secondPageObject.clickButtonYes();
        assertTrue("Ошибка: Текст кнопки не совпадает. ОР: Заказ оформлен",
                secondPageObject.getOrderMessage().contains(exectedOrderText));


    }

    @Test
    public void choiceOrderWithUpButton() {
        HomePageObject homePage = new HomePageObject(driver);
        homePage.open();
        homePage.clickOrderButtonUpOnHomePage();
        WebElement orderWindowElement = driver.findElement(By.xpath(".//div[text()='Для кого самокат']"));
        assertTrue("Ошибка", orderWindowElement.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
