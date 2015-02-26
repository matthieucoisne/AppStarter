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

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.appstarter.AppStarterApplication;

public class PreferencesUtils {

	private PreferencesUtils() {
	}

	public static SharedPreferences getDefaultSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(AppStarterApplication.getAppContext());
	}

	public static SharedPreferences getSharedPreferences(String preferenceName) {
		Context ctx = AppStarterApplication.getAppContext();
		return ctx.getSharedPreferences(ctx.getPackageName() + "_" + preferenceName, Context.MODE_PRIVATE);
	}

	public static void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener prefListener) {
		PreferenceManager.getDefaultSharedPreferences(AppStarterApplication.getAppContext()).registerOnSharedPreferenceChangeListener(prefListener);
	}

	public static void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener prefListener) {
		PreferenceManager.getDefaultSharedPreferences(AppStarterApplication.getAppContext()).unregisterOnSharedPreferenceChangeListener(prefListener);
	}
}