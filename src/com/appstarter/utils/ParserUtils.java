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

/**
 * Class that will help you safely parse data.
 */
public class ParserUtils {
	
	/**
	 * Parses the specified string as a signed decimal long value.
	 * The ASCII character - ('-') is recognized as the minus sign.
	 * If an error is caught, returns 0.
	 * @param stringToParse the string representation of an long value.
	 * @return the primitive long value represented by string.
	 */
	public static long tryParseLong(String stringToParse){
		long ret;
		try {
			ret = Long.parseLong(stringToParse);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}
	
	/**
	 * Parses the specified string as a signed decimal integer value.
	 * The ASCII character - ('-') is recognized as the minus sign.
	 * If an error is caught, returns 0.
	 * @param stringToParse the string representation of an integer value.
	 * @return the primitive integer value represented by string.
	 */
	public static int tryParseInteger(String stringToParse){
		int ret;
		try {
			ret = Integer.parseInt(stringToParse);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}
	
	/**
	 * Parses the specified string as a double value.
	 * If an error is caught, returns 0.
	 * @param stringToParse the string representation of an double value.
	 * @return the primitive double value represented by string.
	 */
	public static double tryParseDouble(String stringToParse){
		double ret;
		try {
			ret = Double.parseDouble(stringToParse);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}
	
	/**
	 * Parses the specified string as a float value.
	 * If an error is caught, returns 0.
	 * @param stringToParse the string representation of an float value.
	 * @return the primitive float value represented by string.
	 */
	public static float tryParseFloat(String stringToParse){
		float ret;
		try {
			ret = Float.parseFloat(stringToParse);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}
	
	/**
	 * Parses the specified string as a boolean value.
	 * @param stringToParse the string representation of a boolean value.
	 * @return the primitive boolean value represented by string.
	 */
	public static boolean tryParseBoolean(String stringToParse){
		boolean ret = false;
		if (stringToParse.equals("1")) {
			ret = true;
		} else if (stringToParse.equalsIgnoreCase("yes")) {
			ret = true;
		} else if (stringToParse.equalsIgnoreCase("true")) {
			ret = true;
		} else if (stringToParse.equalsIgnoreCase("y")) {
			ret = true;
		} else if (stringToParse.equalsIgnoreCase("on")) {
			ret = true;
		}
		return ret;
	}
}