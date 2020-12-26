import common.exception.InvalidBarcodeFormatException;
import controller.BarcodePopupController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class validateBarcodeInputTest {
    private BarcodePopupController barcodeController;

    @BeforeEach
    void setUp() throws Exception {
        barcodeController = new BarcodePopupController();
    }

    @ParameterizedTest
    @CsvSource({
            "1224452236,true",
            "das1144sa5,false",
            ",false"
    })

    void test(String barcode, boolean expected) {
        int bc;
        boolean isValid;
        // when
        try {
            bc = barcodeController.validateBarcodeInput(barcode);
            isValid = true;
        } catch (InvalidBarcodeFormatException e) {
            isValid = false;
        }

        // then
        assertEquals(expected, isValid);
    }
}
