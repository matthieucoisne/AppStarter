package com.appstarter.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.util.Base64;

/**
 * Class that will help you manipulate images.
 * 
 * @author Matthieu Coisne
 */
public class ImageUtils {
	
	private ImageUtils(){
	}
	
	/**
	 * Converts dp to pixels.
	 * @param dp
	 * @return px
	 */
	public static int dpToPx(int dp){
		return (int) (dp * ScreenUtils.getDensity() + 0.5f);
	}
	
	/**
	 * Converts dp to pixels.
	 * @param dp
	 * @return px
	 */
	public static float dpToPx(float dp){
		return (dp * ScreenUtils.getDensity() + 0.5f);
	}
	
	/**
	 * Converts pixels to dp.
	 * @param px
	 * @return dp
	 */
	public static int pxToDp(int px){
		return (int) (px / ScreenUtils.getDensity() + 0.5f);
	}
	
	/**
	 * Encodes a bitmap to a Base64 string.
	 * @param bmp The bitmap to encode.
	 * @return A Base64 string representing the bitmap.
	 */
	public static String encodeToBase64(Bitmap bmp) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
	}
}
