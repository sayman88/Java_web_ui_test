package Work6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserStoryesTest extends AbstractTest{
    JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

    @Test
    @DisplayName("����-���� �4: ����� ������ � ���������� ��� � �������, �������� ��� �� ������� ��� �������")
    public void testCase4() throws InterruptedException {
        // ���� ������ ������ � ����� ���������� �������, ������� ������ � �������� ���������
        new CommodPage(getWebDriver())
                .pressMainMenuItem()
                .pressCategoryItem()
                .pressCommodItem()
                .pressCommodVersionItem()
                .pressAddCommodToBacket()
                .pressBacketBtt();
        assertTrue(new BacketPage(getWebDriver()).getCommodText().equals("Trout Master Ridge Sbiro (12g Floating)"));
        String s = new BacketPage(getWebDriver())
                .pressDelCommod()
                .getPriceText();
        assertTrue(s.equals("0 �."));

        //��������� �����
        logger.info("����-���� �4 �������");      // ��������� ������ ���� ���� �� ������ � ������� ������������
    }

    @Test
    @DisplayName("����-���� �6: �������� ������ ����� ����� ������ ������")
    public void testCase6()  throws InterruptedException {
        // �������� ��������
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        new LoginPage(getWebDriver())
                .setLogin("stendMerlin")
                .setPassword("D2EA_7abd")
                .pressInBtt();
        assertTrue(new MainPage(getWebDriver()).checkUser("���������"));
        logger.debug(" - �������� � 6 : ����������� �������");
        // ������� � ������ �������
        new MainPage(getWebDriver()).pressCabinetBtt();
        // ������� � ������������ ������
        new CabinetPage(getWebDriver())
                .pressPersonlBtt()
                .setNameField("����")
                .setSecondNameField("��������")
                .setLastNameField("������")
                .clearNewPasswordField()
                .pressApplyBtt();

        // � ���������� ����� ��������� ������ ��� ������� � ���������� ������ ����� ������
        // ��� ������������ � ����� �������� �� �����������, ������ � ������� �������� ��������� �����
        // ���������� �������� ���������� ��� ����, ����� ������ ��� ��������

        // ��������
        getWebDriver().get("https://ribomaniya.ru/cabinet/personal/?"); // ���������� ��������
        assertTrue(new MainPage(getWebDriver()).checkUser("����"));
        logger.debug(" - �������� � 6 : ������ ������������ ���������");
        // ������� � ���������� ������ ��������� ������������
        new CabinetPage(getWebDriver())
                .pressPersonlBtt()
                .setNameField("���������")
                .setSecondNameField("�������������")
                .setLastNameField("�����������")
                .clearNewPasswordField()
                .pressApplyBtt();
        getWebDriver().get("https://ribomaniya.ru/cabinet/personal/?"); // ���������� ��������
        assertTrue(new MainPage(getWebDriver()).checkUser("���������"));
        logger.debug(" - �������� � 6 : ������ ��������� ������������ �������������");

        logger.info("����-���� �6 �������");
    }
}
