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

package com.appstarter.exceptions;

import android.util.Log;

import com.appstarter.BuildConfig;

/**
 * AppStarterException.
 * 
 * Your exception class in your project needs to extend this class.
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