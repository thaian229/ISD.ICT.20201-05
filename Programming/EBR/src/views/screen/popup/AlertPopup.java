package views.screen.popup;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utils.Configs;
import utils.Path;
import views.screen.BaseScreenHandler;

import java.io.IOException;

/**
 * class for ...
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
    ImageView icon;

    @FXML
    Label message;


    public AlertPopup(Stage stage) throws IOException {
        super(stage, Path.ALERT_POPUP_PATH);
        setImage(icon, Path.X_ICON);
    }

    private static AlertPopup popup(String message, Boolean undecorated) throws IOException{
        AlertPopup popup = new AlertPopup(new Stage());
        if (undecorated) popup.stage.initStyle(StageStyle.UNDECORATED);
        popup.message.setText(message);
        return popup;
    }

    public static void error(String message) throws IOException{
        popup(message, true).show(true);
    }

    public void show(Boolean autoclose) {
        super.show();
        if (autoclose) close(2);
    }

    public void show(double time) {
        super.show();
        close(time);
    }

    public void close(double time){
        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }
}
