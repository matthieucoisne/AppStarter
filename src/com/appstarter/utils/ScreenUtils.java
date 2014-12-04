package com.appstarter.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.appstarter.AppStarterApplication;

/**
 * Class that will help you get the device screen values.
 * 
 * @author Matthieu Coisne, Benoit Deschanel
 */
public class ScreenUtils {
	
	private int mWidth;
	private int mHeight;
	private float mDensity;
	private int mDensityDpi;
	
	private static ScreenUtils mInstance;
	
	@SuppressLint("NewApi")
	private ScreenUtils(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		mDensity = metrics.density;
		mDensityDpi = metrics.densityDpi;

		if (Build.VERSION.SDK_INT >= 13) {
			Point size = new Point();
			display.getSize(size);
			mWidth = size.x;
			mHeight = size.y;
		}else{
			mWidth = metrics.widthPixels;
			mHeight = metrics.heightPixels;
		}
	}
	
	private static ScreenUtils getInstance(){
		if(mInstance == null){
			mInstance = new ScreenUtils(AppStarterApplication.getAppContext());
		}
		return mInstance;
	}
	
	/**
	 * @return the absolute height of the display in pixels.
	 */
	public static int getHeight(){
		return getInstance().mHeight;
	}
	
	/**
	 * @return the absolute width of the display in pixels.
	 */
	public static int getWidth(){
		return getInstance().mWidth;
	}
	
	/**
	 * <p>The logical density of the display. This is a scaling factor for the Density Independent Pixel unit, 
	 * where one DIP is one pixel on an approximately 160 dpi screen (for example a 240x320, 1.5"x2" screen), 
	 * providing the baseline of the system's display. Thus on a 160dpi screen this density value will be 1; 
	 * on a 120 dpi screen it would be .75; etc.</p>
	 * 
	 * <p>This value does not exactly follow the real screen size (as given by xdpi and ydpi, but rather is used 
	 * to scale the size of the overall UI in steps based on gross changes in the display dpi. For example, 
	 * a 240x320 screen will have a density of 1 even if its width is 1.8", 1.3", etc. However, if the screen 
	 * resolution is increased to 320x480 but the screen size remained 1.5"x2" then the density would be 
	 * increased (probably to 1.5).</p>
	 * 
	 * @return the logical density of the display.
	 */
	public static float getDensity(){
		return getInstance().mDensity;
	}
	
	/**
	 * @return The screen density expressed as dots-per-inch. May be either DENSITY_LOW, DENSITY_MEDIUM, or DENSITY_HIGH.
	 */
	public static int getDensityDpi(){
		return getInstance().mDensityDpi;
	}
}
