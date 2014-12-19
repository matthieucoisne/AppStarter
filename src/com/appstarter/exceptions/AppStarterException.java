package com.appstarter.exceptions;

import android.util.Log;

import com.appstarter.BuildConfig;

/**
 * AppStarterException.
 * 
 * Your exception class in your project needs to extend this class.
 * 
 * @author Matthieu Coisne
 */
public class AppStarterException extends Exception {

	private static final long serialVersionUID = 0xDEAD;
	
	//NETWORK
	public static final int ERROR_NETWORK = -100;
	public static final int ERROR_NETWORK_GET = -101;
	public static final int ERROR_NETWORK_POST = -102;
	//SERVER
	public static final int ERROR_SERVER = -200;

	private int mErrorCode = 0;

	public AppStarterException(int code) {
		super();
		mErrorCode = code;
	}
	
	public AppStarterException(int code, String debugMessage) {
		super();
		mErrorCode = code;
		
		if (BuildConfig.DEBUG) {
			Log.e("AppStarterException", debugMessage);
		}
	}
	
	public AppStarterException(Exception e, int code) {
		super(e);
		mErrorCode = code;
	}
	
	public int getErrorCode(){
		return mErrorCode;
	}
}