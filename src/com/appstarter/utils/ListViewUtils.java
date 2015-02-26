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

import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Class that will help you manipulate ListViews or GridViews.
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
