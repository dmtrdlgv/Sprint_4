package orderPageTests;


import org.junit.Test;
import ru.yandex.practicum.MainPage;
import ru.yandex.practicum.OrderPage;

public class GoToOrderTest extends ExtraStepsOrderPage{

    @Test
    //Проверка открытия страницы заказов через кнопку в хедере страницы
    public void goToTheOrderPageViaHeaderButton() {
        MainPage mainPage = new MainPage(chromeDriver);
        OrderPage orderPage = new OrderPage(chromeDriver);

        mainPage.openMainPage();
        mainPage.clickHeaderOrderButton();
        orderPage.isOpenedOrderPage();

    }

    @Test
    //Проверка открытия страницы заказов через кнопку в содержимом страницы
    public void goToTheOrderPageViaContentButton() {
        MainPage mainPage = new MainPage(chromeDriver);
        OrderPage orderPage = new OrderPage(chromeDriver);

        mainPage.openMainPage();
        mainPage.clickContentOrderButton();
        orderPage.isOpenedOrderPage();


    }


}
