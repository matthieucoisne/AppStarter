package com.appstarter.utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.OrientationEventListener;

/**
 * Orientation Listener.
 * <p><b>Don't forget to call the following methods: enable() and disable()</b></p>
 * 
 * @author Matthieu Coisne
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