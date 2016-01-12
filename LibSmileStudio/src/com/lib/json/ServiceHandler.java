package com.lib.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.lib.Debug;

public class ServiceHandler {
	static String response = null;
	public final static int iGET = 1;
	public final static int iPOST = 2;
	 
	public final static String GET = "GET";
	public final static String POST = "POST";
	public int timeoutMillis = 3000;
	public String user_agent = Browser.Mozilla.getValue();
	private String charset = "utf8";
	
	public ServiceHandler() {
	}

	public String getErrorConver(String response) {
		return response = (response.startsWith("[")) ? response : response.substring(1);
	}
	
	public String GetRespont(String url) {
		Debug.e(url);
		HttpURLConnection connection = null;
		StringBuffer buffer = null;
		try {
			URL web = new URL(url);
			connection = (HttpURLConnection) web.openConnection();
			connection.setRequestMethod(GET);
			connection.setReadTimeout(timeoutMillis);
			connection.setConnectTimeout(timeoutMillis);
			connection.setRequestProperty("User-Agent", user_agent);
			connection.setRequestProperty("Accept", "*/*");
			connection.setDoOutput(false);
			connection.setUseCaches(true);
			connection.setInstanceFollowRedirects(false);
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				Debug.e(connection.getResponseMessage());
				return null;
			}

			InputStream in = connection.getInputStream();
			return readData(in);
		} catch (Exception e) {
			Debug.e("Lỗi: " + e.toString());
			return null;
		} finally {
			connection.disconnect();
		}
	}
	

	/**
	 * Making service call
	 * 
	 * @url - url to make request
	 * @method - http request method
	 * */
	public String makeServiceCall(String url, int method) {
		return this.makeServiceCall(url, method, null);
	}

	/**
	 * Making service call
	 * 
	 * @url - url to make request
	 * @method - http request method
	 * @params - http request params
	 * */
	@Deprecated
	public String makeServiceCall(String url, int method, List<NameValuePair> params) {
		try {
			HttpParams httpParameters = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(httpParameters, 60000);
			HttpConnectionParams.setSoTimeout(httpParameters, 60000);

			HttpClient httpClient = new DefaultHttpClient(httpParameters);

			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			// httpResponse.setHeader("Content-type", "application/json");

			// Checking http request method type
			if (method == iPOST) {
				HttpPost httpPost = new HttpPost(url);
				// adding post params
				if (params != null) {
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				}

				httpResponse = httpClient.execute(httpPost);

			} else if (method == iGET) {
				// appending params to url
				if (params != null) {
					String paramString = URLEncodedUtils.format(params, "utf-8");
					url += "?" + paramString;
				}
				Debug.e("+xxx "+url);
				HttpGet httpGet = new HttpGet(url);
				httpResponse = httpClient.execute(httpGet);
			}
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity);
			return response;
		} catch (Exception e) {
			Debug.e("Lỗi:  "+ e.toString());
			return null;
		}

	}
	
	private String readData(InputStream in) throws Exception {
		StringBuffer buffer = null;
		InputStreamReader isw = new InputStreamReader(in);
		int data = isw.read();
		buffer = new StringBuffer();
		while (data != -1) {
			char current = (char) data;
			data = isw.read();
			buffer.append(current);
		}
		return buffer.toString().replace("\n", "");
	}
}
