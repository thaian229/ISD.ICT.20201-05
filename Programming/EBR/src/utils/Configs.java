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

	// database Configs
//	public static final String DB_NAME = "tqvcknvy";
//	public static final String DB_USERNAME = "tqvcknvy";
//	public static final String DB_PASSWORD = "0vdl180Q2Cv2Rbginla9-ccaxye-gL-S";
//	public static final String DB_URL = "jdbc:postgresql://john.db.elephantsql.com:5432/";
//
	public static final String DB_NAME = "ebr";
	public static final String DB_USERNAME = "postgres";
	public static final String DB_PASSWORD = "04126152";
	public static final String DB_URL = "jdbc:postgresql://localhost/";


    public static String CURRENCY = "VND";
	public static float PERCENT_VAT = 10;
}
