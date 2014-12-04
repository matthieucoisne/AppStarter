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

		if( input != null && input.length > 0) {
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

		if( input != null && input.length > 0) {
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
		for(int x=0; x<list.size(); x++){
			input[x] = list.get(x);
		}
		return implode(input, glue);
	}
	
}
