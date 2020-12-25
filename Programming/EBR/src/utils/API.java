package utils;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * Class for calling http request
 *
 * @author Nguyen Thai An, hieud
 * <p>
 * creted at: 25/11/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class API {

    public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

    /**
     * Get request
     * @param url {@link URL url}
     * @param token token use in authorization
     * @return Http respond
     * @throws Exception errors
     */
    public static String get(String url, String token) throws Exception {
        LOGGER.info("Request URL: " + url + "\n");
        URL line_api_url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        respone.append(inputLine + "\n");
        in.close();
        LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
        return respone.substring(0, respone.length() - 1).toString();
    }

    /**
     * Post request to banking API
     * @param url {@link URL}
     * @param data body of the http request
     * @return Http respond
     * @throws IOException IO errors
     */
    public static String post(String url, String data
//			, String token
    ) throws IOException {
        URL line_api_url = new URL(url);
        LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + data + "\n");
        HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        API.setRequestMethod("PATCH", conn);
        conn.setRequestProperty("Content-Type", "application/json");
//		conn.setRequestProperty("Authorization", "Bearer " + token);
        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(data);
        writer.close();
        BufferedReader in;
        String inputLine;
        if (conn.getResponseCode() / 100 == 2) {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();
        LOGGER.info("Respone Info: " + response.toString());
        return response.toString();
    }

    /**
     * Change the Http request's method to PATCH
     * @param method name of method
     * @param connection {@link HttpURLConnection} connection to be changed method
     * @throws IOException IO errors
     */
    private static void setRequestMethod(String method, HttpURLConnection connection) throws IOException {
        try {
            connection.setRequestMethod(method);
        } catch (ProtocolException e) {
            // JDK only allows one of the fixed set of verbs. Try to override that
            try {
                Field $method = HttpURLConnection.class.getDeclaredField("method");
                $method.setAccessible(true);
                $method.set(connection, method);
            } catch (Exception x) {
                throw (IOException) new IOException("Failed to set the custom verb").initCause(x);
            }
            // sun.net.www.protocol.https.DelegatingHttpsURLConnection delegates to another HttpURLConnection
            try {
                Field $delegate = connection.getClass().getDeclaredField("delegate");
                $delegate.setAccessible(true);
                Object delegate = $delegate.get(connection);
                if (delegate instanceof HttpURLConnection) {
                    HttpURLConnection nested = (HttpURLConnection) delegate;
                    setRequestMethod(method, nested);
                }
            } catch (NoSuchFieldException x) {
                // no problem
            } catch (IllegalAccessException x) {
                throw (IOException) new IOException("Failed to set the custom verb").initCause(x);
            }
        }
        if (!connection.getRequestMethod().equals(method))
            throw new IllegalStateException("Failed to set the request method to " + method);
    }

}
