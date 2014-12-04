package com.appstarter.utils;

import android.os.Bundle;

public class BundleUtils {

	public static String getString(Bundle b, String key) {
		String ret = "";
		if (b != null) {
			ret = b.getString(key);
			if (ret == null) {
				ret = "";
			}
		}
		return ret;
	}
}
