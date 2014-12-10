package com.appstarter.utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;

import android.util.Log;

import com.appstarter.BuildConfig;
import com.appstarter.exceptions.AppStarterException;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * https://github.com/square/okhttp/wiki/Recipes
 */
public final class WebUtils {

	private static String TAG = "AppStarter.WebUtils";

	private WebUtils() {
	}

	public static String doHttpGet(String url) throws AppStarterException {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "doHttpGet - url: " + url);
		}

		String ret = "";
		
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
		client.setReadTimeout(15, TimeUnit.SECONDS);    // socket timeout
		
		try {
			Request request = new Request.Builder().url(new URL(url))
//					.header("User-Agent", "OkHttp Headers.java")
//					.addHeader("Accept", "application/json; q=0.5")
					.build();
			
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new AppStarterException(AppStarterException.ERROR_SERVER);
			}
			ret = response.body().string();
		} catch (IOException e) {
			throw new AppStarterException(e, AppStarterException.ERROR_NETWORK_GET);
		}

		return ret;
	}

	public static String doHttpPost(String url, List<NameValuePair> params) throws AppStarterException {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "doHttpPost - url: " + debugRequest(url, params));
		}

		String ret = "";
		
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
		client.setReadTimeout(15, TimeUnit.SECONDS);    // socket timeout
		
		FormEncodingBuilder builder = new FormEncodingBuilder();
		for (NameValuePair nvp : params) {
			builder.add(nvp.getName(), nvp.getValue());
		}
		RequestBody formBody = builder.build();
		
		try {
			Request request = new Request.Builder().url(new URL(url))
//					.header("User-Agent", "OkHttp Headers.java")
//					.addHeader("Accept", "application/json; q=0.5")
					.post(formBody)
					.build();
			
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new AppStarterException(AppStarterException.ERROR_SERVER);
			}
			ret = response.body().string();
		} catch (IOException e) {
			throw new AppStarterException(e, AppStarterException.ERROR_NETWORK_GET);
		}

		return ret;
	}

	private static String debugRequest(String url, List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append(url);

		if (params != null && params.size() > 0) {
			boolean first = true;
			for (NameValuePair pair : params) {
				sb.append(first ? "?" : "&");
				sb.append(pair.getName());
				sb.append("=");
				sb.append(pair.getValue());
				first = false;
			}
		}
		
		return sb.toString();
	}

//	/**
//	 * Simple network connection check.
//	 * 
//	 * <h1>Warning !</h1> The network the device is connected to may not have
//	 * access to the Internet! i.e. A user without a data plan can be connected
//	 * to a 3G network to make phone calls. If he wants to access the Internet,
//	 * his provider will block the request and display a custom web page. HTTP
//	 * response status code will be 200 (OK) but you will not get the data you
//	 * expect.
//	 */
//	private static boolean isNetworkConnected() {
//		final ConnectivityManager cm = (ConnectivityManager) AppStarterApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//		final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//		return networkInfo != null && networkInfo.isConnected();
//	}
}
