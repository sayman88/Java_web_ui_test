package Work6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends AbstractTest{

    @Test
    @DisplayName("����-���� �1: ����������� �� �����")
    public void testCase1() {
        // �������� ��������
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        new LoginPage(getWebDriver())
                .setLogin("stendMerlin")
                .setPassword("D2EA_7abd")
                .pressInBtt();
        assertTrue(new MainPage(getWebDriver()).checkUser("���������"));
        logger.info("����-���� �1 �������");   // ��������� ������ ���� ���� �� ������ � ������� ������������ assert
    }

    @Test
    @DisplayName("����-���� �2: ����������� �� ����� (���������� ����)")
    public void testCase2() {
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        assertTrue(
                new LoginPage(getWebDriver())
                        .setLogin("stendMerlin")
                        .setPassword("password")        // �������� ������
                        .pressInBtt()
                        .isError()
        );
        logger.info("����-���� �2 �������");
    }



}