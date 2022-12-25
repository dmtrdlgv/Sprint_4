package orderPageTests;

import org.junit.Test;
import ru.yandex.practicum.OrderPage;

public class FlowOrderTest extends ExtraStepsOrderPage{

    @Test
    public void checkPositiveFlowOrderTest(){

        OrderPage orderPage = new OrderPage(chromeDriver);
        orderPage.openOrderPage();
        orderPage.isOpenedOrderPage();

        orderPage.fillingAllFieldsInFirstFormOrder("Тест", "Тестов",
                "г.Москва, улица Сезам",1,"89156415642");

        orderPage.clickNextButton();
        orderPage.isChangedOrderForm();

        orderPage.fillingAllFieldsInSecondFormOrder("27.12.2022", "black", "Тестовый комментарий");

        orderPage.clickOrderButton();
        orderPage.waitModalConfirmOrder();
        orderPage.clickModalConfirmButton();
        orderPage.waitModalSuccessOrder();
        orderPage.checkHeaderModalSuccessOrder();

    }
}
