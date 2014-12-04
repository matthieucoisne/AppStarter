package com.appstarter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.appstarter.AppStarterApplication;

/**
 * @author Matthieu Coisne
 */
public class PreferencesUtils {
	
	private PreferencesUtils(){
	}
	
	public static SharedPreferences getDefaultSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(AppStarterApplication.getAppContext());
	}
	
	public static SharedPreferences getSharedPreferences(String preferenceName) {
		Context ctx = AppStarterApplication.getAppContext();
		return ctx.getSharedPreferences(ctx.getPackageName() + "_"  + preferenceName, Context.MODE_PRIVATE);
	}
	
	public static void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener prefListener) {
		PreferenceManager.getDefaultSharedPreferences(AppStarterApplication.getAppContext()).registerOnSharedPreferenceChangeListener(prefListener);
	}
	
	public static void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener prefListener) {
		PreferenceManager.getDefaultSharedPreferences(AppStarterApplication.getAppContext()).unregisterOnSharedPreferenceChangeListener(prefListener);
	}
}