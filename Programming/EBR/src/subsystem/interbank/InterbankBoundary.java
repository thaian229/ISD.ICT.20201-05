package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.API;

public class InterbankBoundary {

	/**
	 * call API to make HTTP request
	 * @param url {@link java.net.URL}
	 * @param data body of request
	 * @return Http respond
	 */
	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
