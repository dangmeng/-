package com.dm.viewpagerdemo;

import android.content.Context;

public class Utils {
	
	public static int px2Dp(int px,Context context){
		//px/dp = density
		float density = context.getResources().getDisplayMetrics().density;
		int dp = (int) (px * 1f / density + 0.5f);
		return dp;
	}
	
	public static int dp2Px(int dp,Context context){
		//px/dp = density
		float density = context.getResources().getDisplayMetrics().density;
		int px = (int) (dp * density * 1f);
		return px;
	}

}
