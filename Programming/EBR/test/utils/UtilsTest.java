package utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.Utils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    @ParameterizedTest
    @CsvSource({
            "2020-08-08 20:08:59, 2020-08-08 22:10:30, 121",
            "2020-08-09 20:08:59, 2020-08-08 22:10:30, -1318",
            "2020-07-08 20:08:59, 2020-08-08 22:10:30, 44761",
            "2020-02-08 20:08:59, 2020-08-08 22:10:30, 262201",
            "2020-09-08 20:08:59, 2021-02-08 22:10:30, 220441",
    })
    public void minusLocalDateTimeTest(String start, String end, long expected) {
        LocalDateTime startTime = LocalDateTime.parse(start, Utils.DATE_FORMATER);
        LocalDateTime endTime = LocalDateTime.parse(end, Utils.DATE_FORMATER);
        assertEquals(expected, Utils.minusLocalDateTime(startTime,endTime));
    }
}
