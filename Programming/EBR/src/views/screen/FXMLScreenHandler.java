package views.screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

/**
 * Base class for FXML handler
 *
 * @author mhoang99
 * <p>
 * creted at: 20/11/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class FXMLScreenHandler {

	protected FXMLLoader loader;
	protected AnchorPane content;

	/**
	 * Constructor
	 * @param screenPath .fxml path
	 * @throws IOException IO error
	 */
	public FXMLScreenHandler(String screenPath) throws IOException {
		this.loader = new FXMLLoader(getClass().getResource(screenPath));
		// Set this class as the controller
		this.loader.setController(this);
		this.content = (AnchorPane) loader.load();
	}

	/**
	 * get root anchor pane
	 * @return {@link AnchorPane anchorPane}
	 */
	public AnchorPane getContent() {
		return this.content;
	}

	/**
	 * get loader
	 * @return {@link FXMLLoader FXMLLoader}
	 */
	public FXMLLoader getLoader() {
		return this.loader;
	}

	/**
	 * set image from path
	 * @param imv Image Holder on FXML
	 * @param path URI to image
	 */
	public void setImage(ImageView imv, String path){
		File file = new File(path);
		Image img = new Image(file.toURI().toString());
		imv.setImage(img);
	}
}
