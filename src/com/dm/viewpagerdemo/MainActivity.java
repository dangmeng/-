package com.dm.viewpagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity implements OnPageChangeListener {

	private ViewPager mViewPager;
	private LinearLayout mPointContainer;
	private Handler mHandler = new Handler();

	int[] pics = { R.drawable.home01, R.drawable.home02, R.drawable.home03, R.drawable.home04, R.drawable.home05,
			R.drawable.home06, R.drawable.home07, R.drawable.home08 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mViewPager = (ViewPager) findViewById(R.id.viewPager);

		mPointContainer = (LinearLayout) findViewById(R.id.point_container);

		for (int i = 0; i < pics.length; i++) {
			View point = new View(getApplicationContext());
			LayoutParams param = new LayoutParams(Utils.dp2Px(10, this), Utils.dp2Px(10, this));
			param.leftMargin = Utils.dp2Px(5, this);
			if (i == 0) {
				point.setBackgroundResource(R.drawable.point_selected);

			} else {
				point.setBackgroundResource(R.drawable.point_normal);
			}
			mPointContainer.addView(point, param);
		}

		MyPagerAdapter adapter = new MyPagerAdapter();
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);

		int mid = Integer.MAX_VALUE / 2;
		int diff = mid % pics.length;
		int index = mid - diff;
		mViewPager.setCurrentItem(index);

		final AutoScollTask autoScollTask = new AutoScollTask();
		autoScollTask.start();

		mViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:// 按下
					autoScollTask.stop();
					break;
				case MotionEvent.ACTION_UP:// 抬起
					autoScollTask.start();
					break;
				case MotionEvent.ACTION_MOVE:// 移动
					break;
				}
				return false;
			}
		});
	}
	class AutoScollTask implements Runnable {

		public void stop() {
			mHandler.removeCallbacks(this);
		}

		public void start() {
			mHandler.postDelayed(this, 2000);
		}

		@Override
		public void run() {
			int currentItem = mViewPager.getCurrentItem();
			currentItem++;
			mViewPager.setCurrentItem(currentItem);
			// 递归调用
			start();
		}
	}

	class MyPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			if (pics.length != 0) {
				return Integer.MAX_VALUE;
			}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			position = position % pics.length;
			container.removeView((View) object);
			;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			position = position % pics.length;
			ImageView iv = new ImageView(getApplicationContext());
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setImageResource(pics[position]);
			container.addView(iv);
			return iv;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		position = position % pics.length;
		for (int i = 0; i < pics.length; i++) {
			View point = mPointContainer.getChildAt(i);
			if (position == i) {// 当前这一页
				point.setBackgroundResource(R.drawable.point_selected);
			} else {
				point.setBackgroundResource(R.drawable.point_normal);
			}
		}
	}
}
