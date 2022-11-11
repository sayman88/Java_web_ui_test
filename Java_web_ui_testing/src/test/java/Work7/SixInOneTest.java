package Work7;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SixInOneTest extends AbstractTest {
    JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

    // ��������� ����������� ����� �������� ��� ����� ������
    void saveBrowserLogs() {
        LogEntries browserLogs = getWebDriver().manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
        // ����� ����������� ��� ����� � ������ �����, ����� ���� ��� ��������� (����� ����� ��������))
        if (allLogRows.size() > 0) {
            allLogRows.forEach(logEntry -> {
                logger.debug("BROWSERLOGS: "+logEntry.getMessage());
            });
        }
    }

    @Test
    @DisplayName("����-���� �1: ����������� �� �����")
    @Description("����-���� �1: ����������� �� �����")
    @Link("������ �� tms")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("������������ ���������� �����")
    @Feature("����������� �� �����")
    @Story("���� �� ���� �� ����� ������������ � ������")
    public void testCase1() throws IOException {
        // �������� ��������
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        new LoginPage(getWebDriver())
                .setLogin("stendMerlin")
                .setPassword("D2EA_7abd")
                .pressInBtt();
        assertTrue(new MainPage(getWebDriver()).checkUser("���������"));
        // ���������� ����� ��������
        saveBrowserLogs();
        // ���������� ��������� � ������ ������������
        String fileName =  "test-case1-" + System.currentTimeMillis() + ".png";
        CommonUtils.makeScreenshot(getWebDriver(),fileName);
        // �������� �������� ��� �������� � �����
        // ����������� �� ���������� �����
        logger.info("����-���� �1 �������");   // ��������� ������ ���� ���� �� ������ � ������� ������������ assert
    }

    // ��������� junit
    @Test
    @DisplayName("����-���� �2: ����������� �� ����� (���������� ����)")
    // ��������� allure
    @Description("����-���� �1: ����������� �� ����� (���������� ����)")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("������������ ���������� �����")
    @Feature("����������� �� �����")
    @Story("������ ��� �������� ������������ ��� ������")
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
        saveBrowserLogs();
        logger.info("����-���� �2 �������");
    }

    @Test
    @DisplayName("����-���� �3: �������� ������������ ����������� ������ �������� �� ������� ��������")
    // ��������� allure
    @Description("����-���� �3: �������� ������������ ����������� ������ �������� �� ������� ��������")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.TRIVIAL)
    @Epic("������������ ���������� �����")
    @Feature("���������� ������")
    @Story("����� ������������� ����������� ������ � �����")
    public void testCase3() {
        getWebDriver().get("https://ribomaniya.ru");
        assertTrue(
                new MainPage(getWebDriver()).getPhoneNum().equals("8 (800) 350-32-12")
        );
        logger.info("����-���� �3 �������");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menudata.csv")
    @DisplayName("����-���� �5: �������� ������ ������� ����� ��������� �������� �����")
    // ��������� allure
    @Description("����-���� �5: �������� ������ ������� ����� ��������� �������� �����")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.NORMAL)
    @Epic("������������ ���������� �����")
    @Feature("��������� �� ����� � ������� ���� ��������")
    @Story("������� ������������ �/� ��������� �����������")
    public void testCase5(String s_xpath, String s_title, String s_header) throws InterruptedException {
        // �������� �������� ��� ������ ������ �������� ������
        getWebDriver().get("https://ribomaniya.ru");
        MainPage e = new MainPage(getWebDriver());
        assertTrue(e.checkMenuItemTitle(s_xpath,s_title)); // �������� �������� �� �������
        e.pressMenuItem(s_xpath);
        assertTrue(e.checkMenuItemTitle(s_xpath,s_title)); // �������� �������� ����� �������
        assertTrue(e.checkContentHeader(s_header));
        saveBrowserLogs();
        logger.info("����-���� �5 ������� ("+s_title+")");
    }

    @Test
    @DisplayName("����-���� �4: ����� ������ � ���������� ��� � �������, �������� ��� �� ������� ��� �������")
    // ��������� allure
    @Description("����-���� �4: ����� ������ � ���������� ��� � �������, �������� ��� �� ������� ��� �������")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("������������ ���������� ���������������� �������")
    @Feature("�������")
    @Story("����� ������, ���������� ��� � �������, ������� �������")
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
        saveBrowserLogs();
        logger.info("����-���� �4 �������");      // ��������� ������ ���� ���� �� ������ � ������� ������������
    }

    @Test
    @DisplayName("����-���� �6: �������� ������ ����� ����� ������ ������")
    // ��������� allure
    @Description("����-���� �6: �������� ������ ����� ����� ������ ������")
    @Link("https://ribomaniya.ru")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("������������ ���������� ���������������� �������")
    @Feature("���� ����� ���������������� ������ � ��")
    @Story("�������������� ������ � ��")
    public void testCase6()  throws InterruptedException {
        // �������� ��������
        getWebDriver().get("https://ribomaniya.ru");
        // new WebDriverWait(getWebDriver(), 120).until(ExpectedConditions.urlContains(".ru")); // /cabinet/
        // �����������
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
        saveBrowserLogs();
        logger.info("����-���� �6 �������");
    }

}