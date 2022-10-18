package Work4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest{
    Logger logger = LoggerFactory.getLogger("Unit test's");

    @Test
    @DisplayName("���� 1: �������� ���������� ������� �� ������������ ������������")
    void test1() throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("���� 1: �������� ���������� ������� �� ������������ ������������");
        assertFalse(HeronTriangle.getSquare(3,6,7)==0);
        logger.info("\t���� �������");
    }

    @ParameterizedTest
    @CsvSource({"3,6,7,8.94427190999916","-3,-6,-7,8.94427190999916","3,-6,7,8.94427190999916","7,6,7,18.973665961010276"})
    @DisplayName("���� 2: �������� ������������ ���������� ������� �� ������������ �������������")
    void test2(int a, int b, int c, double d) throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("���� 2: �������� ������������ ���������� ������� �� ������������ �������������");
        logger.info("\t�������� ��������� - (a,b,c) - " +a+", "+b+", "+c);
        assertEquals(HeronTriangle.getSquare(a,b,c),d);
        logger.info("\t���� �������");
    }

     @Test
    @DisplayName("���� 3: �������� ������������ ������������ ����������, ����� ����������� �� ����������")
    void test3() throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("���� 3: �������� ������������ ������������ ����������, ����� ����������� �� ����������");
        // ������ ����������� ����� ����������
        assertThrows(InvalidTriangleException.class, ()->HeronTriangle.getSquare(233, 6, 7));
        logger.info("\t���� �������");
    }


}
