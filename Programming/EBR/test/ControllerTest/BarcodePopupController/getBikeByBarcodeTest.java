package ControllerTest.BarcodePopupController;

import controller.BarcodePopupController;
import model.bike.Bike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class getBikeByBarcodeTest {
    private BarcodePopupController barcodePopupController;

    @BeforeEach
    void setUp() throws Exception {
        barcodePopupController = new BarcodePopupController();
    }

    @ParameterizedTest
    @CsvSource({
            "467295, 467295",
            "25121, Bike with this barcode is not exist!",
    })

    void getBikeTest(int barcode, String expected) {
        try {
            Bike bike = barcodePopupController.getBikeByBarcode(barcode);
            Assertions.assertEquals(expected, Integer.toString(bike.getBarcode()));
        } catch (Exception e){
            Assertions.assertEquals(expected, e.getMessage());
        }
    }
}
