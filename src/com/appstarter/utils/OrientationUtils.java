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
import android.content.pm.ActivityInfo;
import android.view.OrientationEventListener;

/**
 * Orientation Listener.
 * <p><b>Don't forget to call the following methods: enable() and disable()</b></p>
 */
public class OrientationUtils extends OrientationEventListener {

	private int mOrientation = -1;
	private IOrientationUtils mCallback;
	private boolean mAllowReversePortrait;

	public OrientationUtils(Context context, int rate, IOrientationUtils callback, boolean allowReversePortrait) {
		super(context, rate);
		mCallback = callback;
		mAllowReversePortrait = allowReversePortrait;
	}

	public OrientationUtils(Context context, IOrientationUtils callback, boolean allowReversePortrait) {
		super(context);
		mCallback = callback;
		mAllowReversePortrait = allowReversePortrait;
	}

	@Override
	public void onOrientationChanged(int orientation) {
		int lastOrientation = mOrientation;

		if (orientation != -1) { // This is to prevent changing orientation to portrait when the device is horizontal
			if (orientation >= 315 || orientation < 45) {
				if (mOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
					mOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
				}
			} else if (orientation < 315 && orientation >= 225) {
				if (mOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
					mOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
				}
			} else if (orientation < 225 && orientation >= 135) {
				if (mOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT && mAllowReversePortrait) {
					mOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
				}
			} else if (orientation < 135 && orientation > 45) {
				if (mOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
					mOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
				}
			}

			if (lastOrientation != mOrientation) {
				mCallback.setOrientation(mOrientation);
			}
		}
	}
	
	public interface IOrientationUtils {
		public void setOrientation(int orientation);
	}
}