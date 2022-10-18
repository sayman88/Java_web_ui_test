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
    @DisplayName("Тест 1: проверка сходимости формулы на существующем треугольнике")
    void test1() throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("Тест 1: проверка сходимости формулы на существующем треугольнике");
        assertFalse(HeronTriangle.getSquare(3,6,7)==0);
        logger.info("\tТест успешен");
    }

    @ParameterizedTest
    @CsvSource({"3,6,7,8.94427190999916","-3,-6,-7,8.94427190999916","3,-6,7,8.94427190999916","7,6,7,18.973665961010276"})
    @DisplayName("Тест 2: проверка корректности вычисления площади на существующих треугольниках")
    void test2(int a, int b, int c, double d) throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("Тест 2: проверка корректности вычисления площади на существующих треугольниках");
        logger.info("\tВходящие параметры - (a,b,c) - " +a+", "+b+", "+c);
        assertEquals(HeronTriangle.getSquare(a,b,c),d);
        logger.info("\tТест успешен");
    }

     @Test
    @DisplayName("Тест 3: проверка корректности срабатывания исключения, когда треугольник не существует")
    void test3() throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("Тест 3: проверка корректности срабатывания исключения, когда треугольник не существует");
        // способ констатации факта исключения
        assertThrows(InvalidTriangleException.class, ()->HeronTriangle.getSquare(233, 6, 7));
        logger.info("\tТест успешен");
    }


}
