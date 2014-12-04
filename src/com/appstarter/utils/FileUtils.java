package com.appstarter.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.appstarter.AppStarterApplication;

public class FileUtils {

	private FileUtils() {
	}

	public static String readFile(String filePath) throws Exception {
		File file = new File(filePath);
		return readFile(file);
	}

	public static String readFile(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		return StringUtils.convertStreamToString(fis);
	}

	public static String readInternalFile(String filename) throws Exception {
		InputStream is = AppStarterApplication.getAppContext().openFileInput(filename);
		return StringUtils.convertStreamToString(is);
	}
}
