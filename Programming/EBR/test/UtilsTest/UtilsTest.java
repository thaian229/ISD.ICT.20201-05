package UtilsTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.Utils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    @ParameterizedTest
    @CsvSource({
            "2020-08-08 20:08:59, 2020-08-08 22:10:30, 7291",
            "2020-08-09 20:08:59, 2020-08-08 22:10:30, -79109",
            "2020-07-08 20:08:59, 2020-08-08 22:10:30, 2685691",
            "2020-02-08 20:08:59, 2020-08-08 22:10:30, 15732091",
            "2020-09-08 20:08:59, 2021-02-08 22:10:30, 13226491",
    })
    public void minusLocalDateTimeTest(String start, String end, long expected) {
        LocalDateTime startTime = LocalDateTime.parse(start, Utils.DATE_FORMATER);
        LocalDateTime endTime = LocalDateTime.parse(end, Utils.DATE_FORMATER);
        System.out.println(startTime);
        System.out.println(endTime);
        assertEquals(expected, Utils.minusLocalDateTime(startTime,endTime));
    }
}
