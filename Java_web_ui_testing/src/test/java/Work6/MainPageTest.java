package Work6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends AbstractTest{

    @Test
    @DisplayName("����-���� �3: �������� ������������ ����������� ������ �������� �� ������� ��������")
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
    public void testCase5(String s_xpath, String s_title, String s_header) throws InterruptedException {
        // �������� �������� ��� ������ ������ �������� ������
        getWebDriver().get("https://ribomaniya.ru");
        MainPage e = new MainPage(getWebDriver());
        assertTrue(e.checkMenuItemTitle(s_xpath,s_title)); // �������� �������� �� �������
        e.pressMenuItem(s_xpath);
        assertTrue(e.checkMenuItemTitle(s_xpath,s_title)); // �������� �������� ����� �������
        assertTrue(e.checkContentHeader(s_header));
        logger.info("����-���� �5 ������� ("+s_title+")");
    }

}