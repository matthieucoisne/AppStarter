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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.content.ContextWrapper;

import com.appstarter.AppStarterApplication;

public class ZipUtils {

	public static interface IZipUtils {
		public void onProgress(int total, int progress, String fileName);
	}

	public static void unzip(File file) throws FileNotFoundException, IOException {
		unzip(file.getName(), file.getPath(), null);
	}

	public static void unzip(File file, IZipUtils callback) throws FileNotFoundException, IOException {
		unzip(file.getName(), file.getPath(), callback);
	}

	public static void unzip(String filename, String filepath) throws FileNotFoundException, IOException {
		unzip(filepath, filepath, null);
	}

	public static void unzip(String filename, String filepath, IZipUtils callback) throws FileNotFoundException, IOException {
		FileOutputStream fout = null;
		FileInputStream fin = null;
		ZipInputStream zin = null;
		ZipEntry ze = null;
		ZipFile file = null;
		int idx = 0;
		int total = 0;
		try {
			_dirChecker(filepath, "");
			file = new ZipFile(filepath + "/" + filename);
			fin = new FileInputStream(filepath + "/" + filename);
			zin = new ZipInputStream(fin);
			ze = null;

			total = file.size();
			while ((ze = zin.getNextEntry()) != null) {
				idx++;

				// Avoid MAC stuff
				if (ze.getName().contains("__MACOSX") || ze.getName().contains("DS_Store")) {
					continue;
				}

				if (callback != null) {
					callback.onProgress(total, idx, ze.getName());
				}

				if (ze.isDirectory()) {
					_dirChecker(filepath, ze.getName());
				} else {
					fout = new FileOutputStream(filepath + ze.getName());
					for (int c = zin.read(); c != -1; c = zin.read()) {
						fout.write(c);
					}
					zin.closeEntry();
					fout.close();
				}
			}
		} finally {
			if (fout != null) {
				fout.close();
			}
			if (zin != null) {
				zin.close();
			}
			if (fin != null) {
				fin.close();
			}
			if (file != null) {
				file.close();
			}
		}
	}

	public static void unzipInternal(File file) throws IOException {
		unzipInternal(file.getName(), null);
	}

	public static void unzipInternal(File file, IZipUtils callback) throws IOException {
		unzipInternal(file.getName(), callback);
	}

	public static void unzipInternal(String filename) throws IOException {
		unzipInternal(filename, null);
	}

	public static void unzipInternal(String filename, IZipUtils callback) throws IOException {
		ContextWrapper cw = new ContextWrapper(AppStarterApplication.getAppContext());
		File directory = cw.getDir("", Context.MODE_PRIVATE);
		String filepath = directory.getPath().replace("app_", "files");

		FileOutputStream fout = null;
		FileInputStream fin = null;
		ZipInputStream zin = null;
		ZipEntry ze = null;
		ZipFile file = null;
		int idx = 0;
		int total = 0;
		try {
			_dirChecker(filepath, "");
			file = new ZipFile(filepath + "/" + filename);
			fin = new FileInputStream(filepath + "/" + filename);
			zin = new ZipInputStream(fin);
			ze = null;

			total = file.size();
			while ((ze = zin.getNextEntry()) != null) {
				idx++;

				// Avoid MAC shit
				if (ze.getName().contains("__MACOSX") || ze.getName().contains("DS_Store")) {
					continue;
				}

				if (callback != null) {
					callback.onProgress(total, idx, ze.getName());
				}

				if (ze.isDirectory()) {
					_dirChecker(filepath, ze.getName());
				} else {
					fout = AppStarterApplication.getAppContext().openFileOutput(ze.getName(), Context.MODE_PRIVATE);
					for (int c = zin.read(); c != -1; c = zin.read()) {
						fout.write(c);
					}
					zin.closeEntry();
					fout.close();
				}
			}
		} finally {
			if (fout != null) {
				fout.close();
			}
			if (zin != null) {
				zin.close();
			}
			if (fin != null) {
				fin.close();
			}
			if (file != null) {
				file.close();
			}
		}
	}

	private static void _dirChecker(String rootDir, String dir) {
		File f = new File(rootDir + dir);
		if (!f.isDirectory()) {
			f.mkdirs();
		}
	}
}
