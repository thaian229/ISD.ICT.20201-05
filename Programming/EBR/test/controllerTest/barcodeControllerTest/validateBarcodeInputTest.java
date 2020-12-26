package controllerTest.barcodeControllerTest;

import controller.BarcodePopupController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * description
 *
 * @author Nguyen Thai An
 * <p>
 * creted at: 26/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class validateBarcodeInputTest {
    private BarcodePopupController barcodeController;

    @BeforeEach
    void setUp() throws Exception {
        barcodeController = new BarcodePopupController();
    }

    @ParameterizedTest
    @CsvSource({
            "1224452236,",
            "das1144sa5, Barcode must contains only number",
            ",ERROR: Barcode is not filled!"
    })

    void test(String barcode, String expected) {
        try {
            barcodeController.validateBarcodeInput(barcode);
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }
    }
}
