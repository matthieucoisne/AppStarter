package com.appstarter.utils;

import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Class that will help you manipulate ListViews or GridViews.
 * 
 * @author Benoit Deschanel
 */
public class ListViewUtils {

	public static void updateRowView(ListView list, Object object) {
		int start = list.getFirstVisiblePosition();
		for (int i = start, j = list.getLastVisiblePosition(); i <= j; i++) {
			if (object == list.getItemAtPosition(i)) {
				View view = list.getChildAt(i - start);
				list.getAdapter().getView(i, view, list);
				break;
			}
		}
	}

	public static void updateRowView(ListView list, int position) {
		View view = list.getChildAt(position);
		list.getAdapter().getView(position, view, list);
	}

	public static void updateCellView(GridView grid, Object object) {
		int start = grid.getFirstVisiblePosition();
		for (int i = start, j = grid.getLastVisiblePosition(); i <= j; i++) {
			if (object == grid.getItemAtPosition(i)) {
				View view = grid.getChildAt(i - start);
				grid.getAdapter().getView(i, view, grid);
				break;
			}
		}
	}

	public static void updateCellView(GridView grid, int position) {
		View view = grid.getChildAt(position);
		grid.getAdapter().getView(position, view, grid);
	}

	public static void updateVisibleRowViews(ListView list) {
		int start = list.getFirstVisiblePosition();
		for (int i = start, j = list.getLastVisiblePosition(); i <= j; i++) {
			View view = list.getChildAt(i - start);
			list.getAdapter().getView(i, view, list);
		}
	}
}
