package com.appstarter.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

import com.appstarter.BuildConfig;

/**
 * Class that will help you manipulate Strings.
 * 
 * @author Matthieu Coisne
 */
public class StringUtils {

	private static String TAG = StringUtils.class.getSimpleName();

	private StringUtils() {
	}

	/**
	 * Google's Method to convert Stream to String
	 * 
	 * @param is
	 *            InputStream to convert.
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		String ret = "";
		if (is != null) {
			try {
				ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
				byte[] buffer = new byte[1024];
				int count;
				do {
					count = is.read(buffer);
					if (count > 0) {
						out.write(buffer, 0, count);
					}
				} while (count >= 0);
				ret = out.toString();
			} catch (IOException e) {
				if (BuildConfig.DEBUG) {
					Log.e(TAG, "convertStreamToString error: " + e.toString());
				}
				ret = "";
			} finally {
				try {
					is.close();
				} catch (IOException ignored) {
				}
			}
		}
		return ret;
	}
	
	public static String encodeUrl(String url) {
		String ret = "";
		try {
			ret = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return ret;
	}

	/**
	 * Format a numbers like: 1,092.4k
	 * */
	public static String numberToKilo(int x) {
		if (x > 1000) {
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setGroupingUsed(true);
			numberFormat.setMaximumFractionDigits(1);
			return numberFormat.format(x / 1000.0) + "k";
		} else
			return x + "";
	}

	/**
	 * Format a numbers like: 1,092.3M
	 * */
	public static String numberToMeg(int x) {
		if (x > 1000000) {
			NumberFormat numberFormat = getSimpleNumberFormater();
			numberFormat.setMaximumFractionDigits(1);
			return numberFormat.format(x / 1000000.0) + "M";
		} else
			return x + "";
	}

	/**
	 * Formater, format a numbers like: 1,092,232.09
	 * */
	public static NumberFormat getSimpleNumberFormater() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setGroupingUsed(true);
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat;
	}

	/**
	 * Format a numbers like: 1,092,232.09
	 * */
	public static String doSimpleFormat(int x) {
		return getSimpleNumberFormater().format(x);
	}

	/**
	 * Format dates like: Jan 12, 2012
	 * */
	public static SimpleDateFormat getSimpleDateFormat() {
		return new SimpleDateFormat("MMM dd, yyyy", Locale.US);
	}

	/**
	 * Format dates like: Jan 12, 2012
	 * */
	public static void doFormatDate(Date date) {
		getSimpleDateFormat().format(date);
	}

	/**
	 * Mixup char into a String
	 * @param original string
	 * @return jumbled
	 */
	public static String Jumble(String original) {
		StringBuilder sb = new StringBuilder(original);

		char temp;
		int swapWith;
		for (int i = 0; i < sb.length(); i++) {
			temp = sb.charAt(i);
			swapWith = (int) Math.floor(Math.random() * sb.length());
			sb.setCharAt(i, sb.charAt(swapWith));
			sb.setCharAt(swapWith, temp);
		}

		return sb.toString();
	}
}
