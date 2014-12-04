package com.appstarter;

import android.app.Application;
import android.content.Context;

/**
 * AppStarterApplication
 * 
 * Your application class in your project needs to extend this class.
 * 
 * @author Matthieu Coisne
 */
public class AppStarterApplication extends Application {

	private static Context mContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
	}
	
	/**
	 * @return The application context.
	 */
	public static Context getAppContext() {
		return mContext;
	}
}
