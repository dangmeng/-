# -
Viewpager无限轮播
xml很简单 

    <android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </android.support.v4.view.ViewPager>
    
    <LinearLayout 
        android:gravity="center"
        android:layout_alignParentBottom="true"
        android:orientation="horizontal"
        android:id="@+id/point_container"
        android:layout_width="match_parent"
        android:layout_height="20dp"
        android:background="#55cccccc"/>
 主要逻辑看我写的DEMO
