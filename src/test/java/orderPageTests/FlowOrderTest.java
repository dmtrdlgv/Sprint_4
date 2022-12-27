package orderPageTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.OrderPage;


@RunWith(Parameterized.class)
public class FlowOrderTest extends ExtraStepsOrderPage{
    private final String firstname;
    private final String lastname;
    private final String address;
    private final int itemNumberMetroStation;
    private final String phoneNumber;
    private final String orderDate;
    private final String colour;
    private final String comment;

    public FlowOrderTest(String firstname, String lastname, String address, int itemNumberMetroStation,
                         String phoneNumber, String orderDate, String colour, String comment) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.itemNumberMetroStation = itemNumberMetroStation;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Тест", "Тестов", "г.Москва, улица Сезам", 1, "89156415642", "27.12.2022", "black", "Тестовый комментарий"},
                {"тест", "тестов", "г.Москва, улица Сезам", 3, "+79226415642", "20.10.2022", "grey", "Тестовый комментарий"}
        };
    }

    @Test
    public void checkPositiveFlowOrderTest(){

    OrderPage orderPage = new OrderPage(chromeDriver);
    orderPage.openOrderPage();
    orderPage.isOpenedOrderPage();

    orderPage.fillingAllFieldsInFirstFormOrder(firstname, lastname,
        address,itemNumberMetroStation,phoneNumber);

    orderPage.clickNextButton();
    orderPage.isChangedOrderForm();

    orderPage.fillingAllFieldsInSecondFormOrder(orderDate, colour, comment);

    orderPage.clickOrderButton();
    orderPage.waitModalConfirmOrder();
    orderPage.clickModalConfirmButton();
    orderPage.waitModalSuccessOrder();
    orderPage.checkHeaderModalSuccessOrder();

    }
}
