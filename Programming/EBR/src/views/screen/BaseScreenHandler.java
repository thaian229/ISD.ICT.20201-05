package views.screen;

import controller.BaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.util.Hashtable;

/**
 * Base class for most screen handlers
 *
 * @author mhong99
 * <p>
 * creted at: 20/11/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class BaseScreenHandler extends views.screen.FXMLScreenHandler {

	private Scene scene;

	private BaseScreenHandler prev;
	protected final Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected Hashtable<String, String> messages;
	private BaseController bController;
	protected String screenTitle = "";

	/**
	 *
	 * @return screen title
	 */
	public String getScreenTitle() {
		return this.screenTitle;
	}

	/**
	 *
	 * @param prev Handler of previous screen
	 */
	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	/**
	 *
	 * @return {@link BaseScreenHandler}
	 */
	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	/**
	 * constructor
	 * @param stage {@link Stage}
	 * @param screenPath path to .fxml file
	 * @throws IOException IO error
	 */
	public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}

	/**
	 * display the screen
	 */
	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
		this.setScreenTitle(this.screenTitle);
	}

	/**
	 *
	 * @param string title to be set
	 */
	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	/**
	 *
	 * @param bController Controller to be set
	 */
	public void setBController(BaseController bController){
		this.bController = bController;
	}

	/**
	 *
	 * @return {@link BaseScreenHandler}
	 */
	public BaseController getBController(){
		return this.bController;
	}

	/**
	 * Store message / info
	 * @param messages {@link Hashtable} info to be set / sent
	 */
	public void forward(Hashtable messages) {
		this.messages = messages;
	}

	/**
	 *
	 * @param HomeScreenHandler Handler of Home screen
	 */
	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}

}
