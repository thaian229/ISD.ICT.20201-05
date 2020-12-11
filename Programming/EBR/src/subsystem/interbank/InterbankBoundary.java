package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.API;
import utils.Utils;

public class InterbankBoundary {

	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, Utils.md5(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
