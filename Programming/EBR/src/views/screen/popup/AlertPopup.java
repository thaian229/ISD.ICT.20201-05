package views.screen.popup;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utils.Path;

import java.io.IOException;

/**
 * class for Popup message
 *
 * @author mHoang
 * <p>
 * created_at: 24/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */
public class AlertPopup extends Popup {
    @FXML
    ImageView tickicon;

    @FXML
    Label message;


    public AlertPopup(Stage stage) throws IOException {
        super(stage, Path.ALERT_POPUP_PATH);
    }

    /**
     * Create new popup alert
     * @param message message to be shown
     * @param undecorated display render type
     * @return {@link AlertPopup alertPopup}
     * @throws IOException IO error
     */
    private static AlertPopup popup(String message, Boolean undecorated) throws IOException{
        AlertPopup popup = new AlertPopup(new Stage());
        if (undecorated) popup.stage.initStyle(StageStyle.UNDECORATED);
        popup.message.setText(message);
//        popup.setImage(imagepath);
        return popup;
    }

    /**
     * show error text
     * @param message error message
     * @throws IOException IO error
     */
    public static void error(String message) throws IOException{
        popup(message, true).show(true);
    }

    public static AlertPopup loading(String message) throws IOException{
        return popup(message, true);
    }

    /**
     * show then auto close the popup
     * @param autoClose auto-close or not
     */
    public void show(Boolean autoClose) {
        super.show();
        if (autoClose) close(2);
    }

    /**
     * show then auto close the popup
     * @param time delay close time in seconds
     */
    public void show(double time) {
        super.show();
        close(time);
    }

    /**
     * close the popup
     * @param time time to close
     */
    public void close(double time){
        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }
}
