package ru.yandex.practicum;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MainPage {

    private final WebDriver driver;

    //Заголовок главной страницы "Самокат на пару дней"
    private final By headMainPage = By.xpath(".//div[@class='Home_Header__iJKdX' and starts-with(text(), 'Самокат')]");

    //Заголовок "Вопросы о важном"
    private final By aboutImportantCaption = By.cssSelector(".Home_FourPart__1uthg .Home_SubHeader__zwi_E");

    //Кнопка выпадающего текста (найдет все кнопки)
    private final By accordionButton = By.xpath(".//div[starts-with(@id,'accordion__heading-')]");

    //Выпадающий элемент (найдет все элементы)
    private final By accordionItem = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']");

    //Кнопка "Заказать" в хедере страницы
    private final By headerOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");

    //Кнопка "Заказать" в содержимом страницы
    private final By middleContentOrderButton = By.cssSelector(".Button_Button__ra12g Button_Middle__1CSJM");

    //Кнопка согласия куки
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Шаг открыть главную страницу на весь экран и закрыть сообщение куки
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
        driver.findElement(cookieButton).click();
    }

    //Проверка открытия главной страницы
    public void isOpenMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(headMainPage));
    }

    //Проверка наличия блока "Вопросы о важном" на странице и прокрутка к нему
    public void checkAboutImportantCaption(){
        WebElement caption = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(aboutImportantCaption));
        new Actions(driver)
                .scrollToElement(caption)
                .perform();
    }

    //Клик на кнопку выпадающего текста в разделе "Вопросы о важном"
    public void clickAccordionButton(int itemNumber) {
        List<WebElement> listAccordionButton = driver.findElements(accordionButton);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(listAccordionButton.get(itemNumber))).click();
    }

    //Проверить, что выпадающий элемент отобразился на странице
    public void isAccordionItemVisible(int itemNumber) {
        List<WebElement> listAccordionItem = driver.findElements(accordionItem);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(listAccordionItem.get(itemNumber)));
    }

    //Cравнить текст в выпадающем элементе с ожидаемым
    public void isEqualTextAccordionItem(int itemNumber, String expectedAccordionItem) {
        List<WebElement> listAccordionItem = driver.findElements(accordionItem);
        MatcherAssert.assertThat("Текст из выпадающего элемента соответствует ожидаемомоу", expectedAccordionItem,
                    is(listAccordionItem.get(itemNumber).getText()));
        }

    //Шаг проверки отображения соответствующего текста в выпадающем элементе
    public void checkTextAccordionItem(int itemNumber, String expectedAccordionItem) {
        clickAccordionButton(itemNumber);
        isAccordionItemVisible(itemNumber);
        isEqualTextAccordionItem(itemNumber, expectedAccordionItem);
    }





}