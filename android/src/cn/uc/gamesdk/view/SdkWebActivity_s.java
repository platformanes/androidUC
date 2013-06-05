package cn.uc.gamesdk.view;

import android.content.pm.ActivityInfo;
import android.view.KeyEvent;

/**
 * @author Rect
 * @version  Time：2013-5-22 
 */
public class SdkWebActivity_s extends SdkWebActivity {
	//屏幕模式 静态变量
	public static int  SCREEN_ORIENTATION;
	@Override
	public void setRequestedOrientation(int i)
	{
		// 0 竖屏 1 横屏 2 自由
		if(SCREEN_ORIENTATION == 0)
			super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
		else if(SCREEN_ORIENTATION == 1)
			super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		else
			super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
	}
	@Override  
	public boolean onKeyDown(int keyCode, KeyEvent event)  
	{  
		if (keyCode == KeyEvent.KEYCODE_HOME )  
		{  
		}  
		return super.onKeyDown(keyCode, event);

	} 
}
