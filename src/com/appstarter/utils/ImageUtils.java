/*
 * Copyright (C) 2011 Matthieu Coisne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appstarter.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.util.Base64;

/**
 * Class that will help you manipulate images.
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
