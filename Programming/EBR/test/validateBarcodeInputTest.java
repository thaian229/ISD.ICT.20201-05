import controller.BarcodeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class validateBarcodeInputTest {
    private BarcodeController barcodeController;

    @BeforeEach
    void setUp() throws Exception {
        barcodeController = new BarcodeController();
    }

    @ParameterizedTest
    @CsvSource({
            "1224452236,true",
            "das1144sa5,false",
            ",false"
    })

    void test(String address, boolean expected) {
        // when
        boolean isValid = barcodeController.validateBarcodeInput(address);
        // then
        assertEquals(expected, isValid);
    }
}
