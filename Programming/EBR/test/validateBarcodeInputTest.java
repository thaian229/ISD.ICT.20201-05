import controller.BarcodeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class validateBarcodeInputTest {
    private BarcodeController barcodeController;

    @BeforeEach
    void setUp() throws Exception {
        barcodeController = new BarcodeController();
    }

    @ParameterizedTest
    @CsvSource({
            "1224452236,",
            "das1144sa5,INVALID: Barcode must contains only number",
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
