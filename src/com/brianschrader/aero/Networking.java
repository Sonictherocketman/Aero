package com.brianschrader.aero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A collection of common networking utilities.
 * 
 * @author Brian Schrader
 * 
 */
public class Networking {

	private static final String USER_AGENT = "Java/1.7.x";

	/******************************************************************************************************************************/
	/******************************************** HTTP Methods ********************************************************************/
	/******************************************************************************************************************************/

	/**
	 * A simple GET request that returns a text only response.
	 * 
	 * @author Brian Schrader
	 * @since June 23, 2014
	 * @see HttpURLConnection
	 * @param url
	 * @return reponse
	 * @throws Exception
	 */
	public static String doGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String resp = response.toString();
		return resp;

	}

	/******************************************************************************************************************************/
	/**************************************** JSON Parsing Methods ****************************************************************/
	/******************************************************************************************************************************/

	/**
	 * A Java implementation of the jQuery getJson function.
	 * 
	 * @author Brian Schrader
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getJSONFromUrl(String url) throws Exception, JSONException {
		String resp = doGet(url);
		return new JSONObject(resp);
	}

	/**
	 * Returns a Java Object of the type specified that contains the values from
	 * the corresponding JSON response. UNTESTED
	 * 
	 * @author Brian Schrader
	 * @param url
	 * @param aClass
	 * @return
	 * @throws Exception
	 */
	public static Object getObjectFromUrl(String url, Class<?> aClass) throws Exception {
		return getObjectFromJSON(getJSONFromUrl(url), aClass);
	}

	/**
	 * Pass it a JSON object and a Class name, it gives you an object of that
	 * class all nicely filled out with the data from the JSON. UNTESTED!
	 * 
	 * @author Brian Schrader
	 * @param json
	 * @param aClass
	 * @return
	 * @throws Exception
	 */
	public static Object getObjectFromJSON(JSONObject json, Class<?> aClass) throws Exception {
		Object object = aClass.newInstance();
		Method[] methods = aClass.getDeclaredMethods();
		if (methods != null) {
			for (Method method : methods) {
				// Find the members of the class and match them to the keys in
				// the JSON.
				Pattern p = Pattern.compile("set(.+)", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(method.getName());
				String member = "";
				while (m.find()) {
					member = m.group(1);
				}
				// Search the JSON for the method name.
				for (String key : JSONObject.getNames(json)) {
					if (key.equalsIgnoreCase(member)) {
						// The key is in the JSON and in the class.
						Object[] params = { (Object) json.get(key) };
						if (!method.isAccessible()) {
							method.setAccessible(true);
						}
						// Set the value of the object for the key.
						try {
							method.invoke(object, params);
						} catch (Exception e) {
							System.out.println(object);
							System.out.println(json.get(key));
							e.printStackTrace();
						}
					}
				}
			}
		}
		return (Object) object;
	}

}
