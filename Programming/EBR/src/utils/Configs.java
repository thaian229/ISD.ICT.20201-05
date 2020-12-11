package utils;

/**
 * @author nguyenlm Contains the configs for AIMS Project
 */
public class Configs {

	// api constants
	public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
	public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
	public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";

	// demo data
	public static final String POST_DATA = "{"
			+ " \"secretKey\": \"BUXj/7/gHHI=\" ,"
			+ " \"transaction\": {"
			+ " \"command\": \"pay\" ,"
			+ " \"cardCode\": \"118609_group1_2020\" ,"
			+ " \"owner\": \"Group 1\" ,"
			+ " \"cvvCode\": \"185\" ,"
			+ " \"dateExpried\": \"1125\" ,"
			+ " \"transactionContent\": \"Pei debt\" ,"
			+ " \"amount\": 50000 "
			+ "}"
			+ "}";
	public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";

	// database Configs
	public static final String DB_NAME = "aims";
	public static final String DB_USERNAME = System.getenv("DB_USERNAME");
	public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

	public static String CURRENCY = "VND";
	public static float PERCENT_VAT = 10;

	// static resource
	public static final String IMAGE_PATH = "assets/images";
	public static final String BLANK_SCREEN_PATH = "/views/fxml/blank.fxml";
	public static final String INVOICE_SCREEN_PATH = "/views/fxml/invoice.fxml";
	public static final String SESSION_SCREEN_PATH = "/views/fxml/session.fxml";
	public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
	public static final String HOME_PATH = "/views/fxml/home.fxml";
	public static final String POPUP_PATH = "/views/fxml/popup.fxml";
	public static final String DOCK_LIST_ITEM_PATH = "/views/fxml/dock_list_item.fxml";
	public static final String PAYMENT_CONFIRMATION_SCREEN_PATH = "/views/fxml/payment_confirmation_screen.fxml";
	public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment_screen.fxml";
	public static final String DOCK_PATH = "/views/fxml/dock.fxml";
	public static final String BIKE_PATH = "/views/fxml/bike.fxml";
	public static final String BIKE_LIST_ITEM_PATH = "/views/fxml/bike_list_item.fxml";
	public static final String BIKE_VIEW_SCREEN_PATH = "/views/fxml/bike.fxml";
	public static final String RETURNING_DOCK_LIST_ITEM_PATH = "/views/fxml/returning_dock_list_item.fxml";
	public static final String RETURNING_DOCK_SELECTION_SCREEN_PATH = "/views/fxml/returning_dock_selection.fxml";
}
