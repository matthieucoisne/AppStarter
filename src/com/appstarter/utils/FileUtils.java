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
