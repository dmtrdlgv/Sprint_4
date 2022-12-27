package ru.yandex.practicum;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class OrderPage {

    WebDriver driver;

    private final String BLACKCOLOUR = "black";
    private final String GREYCOLOUR = "grey";

    //Инпут "Имя"
    private final By firstnameCustomerInput = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");

    //Инпут "Фамилия"
    private final By lastnameCustomerInput = By.xpath(".//input[@placeholder='* Фамилия']");

    //Инпут "Адрес"
    private final By addressCustomerInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Селект "Станция метро"
    private final By metroStationCustomerSelect = By.xpath(".//input[@placeholder='* Станция метро']");

    //Список селекта "Станция метро"
    private final By metroStationCustomerList = By.xpath(".//div[@class='select-search__select']");

    //Элемент списка (вернет список всех элементов)
    private final By metroStationCustomerItem = By.xpath(".//li/button/div[@class='Order_Text__2broi']");

    //Инпут "Телефон"
    private final By phoneCustomerInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка Далее в блоке "Для кого самокат"
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");

    //Заголовок Формы ввода. (Текст заголовка меняется при переходе к следующему набору инпутов)
    private final By headerOrderForm = By.xpath(".//div[@class='Order_Header__BZXOb']");

    //Инпут Даты привоза самоката "Когда привезти самокат"
    private final By dateOfDeliveryScooterInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //кнопка выбранной даты календаря
    private final By dateOfDeliveryScooterButton = By.xpath(".//div[starts-with(@class,'react-datepicker__day react-datepicker__day') and @tabindex='0']");

    //Селект "Срок аренды" самоката
    private final By scooterRentalTermSelect = By.xpath(".//div[@class='Dropdown-placeholder']");

    //Список значений "Срок аренды" самоката (Вернет все элементы списка)
    private final By scooterRentalTermList = By.xpath(".//div[@class='Dropdown-option']");

    //Блок выбора цвета самоката

    //Чек-бокс выбора цвета самоката "чёрный жемчуг"
    private final By scooterColourBlackCheckBox = By.xpath("//input[@id='black']/..");

    //Чек-бокс выбора цвета самоката "серая безысходность"
    private final By scooterColourGreyCheckBox = By.xpath("//input[@id='grey']");

    //Инпут Комментария к заказу
    private final By commentOrderInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Кнопка Далее в блоке "Для кого самокат"
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //Заголовок модального окна подтверждения заказа
    private final By modalConfirmOrderHeader = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");

    //Кнопка подтверждения заказа "Да"
    private final By modalConfirmOrderButton = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");

    //Заголовок модального окна успешного оформления заказа
    private final By modalSuccessfulOrderHeader = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ'  and text()='Заказ оформлен']");

    //Кнопка согласия куки
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Шаг. Открытие страницы заказов
    public void openOrderPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        driver.manage().window().maximize();
        driver.findElement(cookieButton).click();
    }

    //Проверка открытия страницы заказа
    public void isOpenedOrderPage(){
        assertEquals("Заголовок страницы заказа - Для кого самокат", "Для кого самокат",
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(headerOrderForm)).getText());
    }

    //Заполнение поля "Имя"
    public void enterFirstnameCustomerInput(String firstname) {
        driver.findElement(firstnameCustomerInput).sendKeys(firstname);
    }

    //Заполнение поля "Фамилия"
    public void enterLastnameCustomerInput(String lastname) {
        driver.findElement(lastnameCustomerInput).sendKeys(lastname);
    }

    //Заполнение поля "Адрес"
    public void enterAddressCustomerInput(String Address) {
        driver.findElement(addressCustomerInput).sendKeys(Address);
    }

    //Нажатие на селект выбора станций метро
    public void customerMetroStationSelectClick() {
        driver.findElement(metroStationCustomerSelect).click();
    }

    //Ожидание появления списка станций метро
    public void waitForMetroStationCustomerList(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(metroStationCustomerList));
    }

    //Выбор станции метро из списко по номеру записи и возврат значения
    public String selectCustomerMetroStation(int itemNumber) {
        List<WebElement> metroStationItem = driver.findElements(metroStationCustomerItem);
                new Actions(driver)
                .scrollToElement(metroStationItem.get(itemNumber))
                .perform();
        String metroStation =  metroStationItem.get(itemNumber).getText();
        metroStationItem.get(itemNumber).click();
        return metroStation;
    }

    //Шаг. Проверка, что выбранный элемент из списка элемент появился в инпуте Станции метро
    public void isSelectedMetroStation(int itemNumber) {
        customerMetroStationSelectClick();
        waitForMetroStationCustomerList();
        String metroStation = selectCustomerMetroStation(itemNumber);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(metroStationCustomerList));
        assertEquals(driver.findElement(metroStationCustomerSelect).getAttribute("Value"), metroStation);
    }

    //Ввод номера телефона
    public void enterPhoneNumber(String phoneNumber){
        driver.findElement(phoneCustomerInput).sendKeys(phoneNumber);
    }

    //Шаг заполнение всех полей первой формы заказа "Для кого самокат"
    public void fillingAllFieldsInFirstFormOrder(String firstname, String lastname,String address, int itemNumberMetroStation, String phoneNumber) {
        enterFirstnameCustomerInput(firstname);
        enterLastnameCustomerInput(lastname);
        enterAddressCustomerInput(address);
        isSelectedMetroStation(itemNumberMetroStation);
        enterPhoneNumber(phoneNumber);
    }

    //Нажать на "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Проверка смены формы заполнения заказа
    public void isChangedOrderForm(){
        assertEquals("Заголовок страницы заказа - Про аренду", "Про аренду",
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(headerOrderForm)).getText());
    }

    //Заполнение инпута Даты заказа
    public void enterOrderDateInput(String orderDate) {
        driver.findElement(dateOfDeliveryScooterInput).sendKeys(orderDate);
    }

    //Нажатие на кнопку даты в календаре
    public void clickOrderDateCalendarButton() {
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(dateOfDeliveryScooterButton)).click();
    }

    //Шаг. Заполнение поля даты заказа
    public void fillingInputOrderDate(String orderDate) {
        enterOrderDateInput(orderDate);
        clickOrderDateCalendarButton();

    }


    //Нажатие на селект Срока аренды
    public void scooterRentalTermClick(){
        driver.findElement(scooterRentalTermSelect).click();
    }

    //ожидание появления списка Срока аренды
    public void waitScooterRentalTermList() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(scooterRentalTermList));
    }

    //Выбор значения из селекта Срока аренды
    public void selectItemScooterRentalTerm(){
        driver.findElement(scooterRentalTermList).click();
    }

    //Шаг заполнения поля срока аренды
    public void fillingScooterRentalTerm(){
        scooterRentalTermClick();
        waitScooterRentalTermList();
        selectItemScooterRentalTerm();
    }

    //Включение чек-бокса черного цвета
    public void enablingBlackCheckBox() {
        driver.findElement(scooterColourBlackCheckBox).click();
    }

    //Включение чек-бокса серого цвета
    public void enablingGreyCheckBox() {
        driver.findElement(scooterColourGreyCheckBox).click();
    }

    //Включение чек-бокса выбора одного из цветов
    public void enablingOneOfColourCheckBox(String colour) {
        if (colour.equals(GREYCOLOUR)) {
            enablingGreyCheckBox();
        } else if (colour.equals(BLACKCOLOUR)) {
            enablingBlackCheckBox();
        }
    }

    //Ввод комментария к заказу
    public void enterOrderComment(String comment) {
        driver.findElement(commentOrderInput).sendKeys(comment);
    }

    //Шаг. Заполнения всех полей второй формы заказа
    public void fillingAllFieldsInSecondFormOrder(String orderDate, String colour, String comment){
        fillingInputOrderDate(orderDate);
        fillingScooterRentalTerm();
        enablingOneOfColourCheckBox(colour);
        enterOrderComment(comment);
    }


    //Нажать на кнопку "Заказать"
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }

    //Ожидание появления модального окна подтверждения заказа
    public void waitModalConfirmOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(modalConfirmOrderHeader));
    }

    //Нажатие на кнопку подтверждение Заказа
    public void clickModalConfirmButton() {
        driver.findElement(modalConfirmOrderButton).click();
    }

    //Ожидание появления модального окна успешного заказа
    public void waitModalSuccessOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(modalSuccessfulOrderHeader));
    }

    //Проверка текста заголовка модального окна успешного заказа
    public void checkHeaderModalSuccessOrder(){
        assertThat(driver.findElement(modalSuccessfulOrderHeader).getText(),containsString("Заказ оформлен"));

    }


}
