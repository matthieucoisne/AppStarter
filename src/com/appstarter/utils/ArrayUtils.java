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

import java.util.ArrayList;

public class ArrayUtils {

	/**
	 * Method to join array elements of type string
	 * 
	 * @param input
	 *            Array which contains strings
	 * @param glue
	 *            String between each array element
	 * @return String containing all array elements separated by glue string
	 */
	public static String implode(String[] input, String glue) {
		String output = "";

		if (input != null && input.length > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(input[0]);

			for (int i = 1; i < input.length; i++) {
				sb.append(glue);
				sb.append(input[i]);
			}
			output = sb.toString();
		}

		return output;
	}
	
	/**
	 * Method to join array elements of type int
	 * 
	 * @param input
	 *            Array which contains int
	 * @param glue
	 *            String between each array element
	 * @return String containing all array elements separated by glue string
	 */
	public static String implode(int[] input, String glue) {
		String output = "";

		if (input != null && input.length > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(input[0]);

			for (int i = 1; i < input.length; i++) {
				sb.append(glue);
				sb.append(input[i]);
			}
			output = sb.toString();
		}

		return output;
	}
	
	
	/**
	 * Method to join array elements of type string
	 * 
	 * @param input
	 *            Array which contains strings
	 * @param glue
	 *            String between each array element
	 * @return String containing all array elements separated by glue string
	 */
	public static String implode(ArrayList<String> list, String glue) {
		String[] input = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			input[i] = list.get(i);
		}
		return implode(input, glue);
	}
	
}
