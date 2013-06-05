package cn.uc.gamesdk.ane;

import android.util.Log;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCOrientation;
import cn.uc.gamesdk.view.SdkWebActivity_s;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 设置屏幕方向（0=竖屏，1=横屏）。默认为竖屏（0）。
 */
public class UCFSetOrientation implements FREFunction {
    private final static String TAG = "UCFSetOrientation";
    
	//竖屏
	public final static int ORIENTATION_PORTRAIT = 0;
	//横屏
	public final static int ORIENTATION_LANDSCAPE = 1;
	

	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFSetOrientation calling...");
		try {
			int orientation = args[0].getAsInt();
			
			SdkWebActivity_s.SCREEN_ORIENTATION = orientation;
			
			UCOrientation ucOrientation = UCOrientation.PORTRAIT;
	    	if (orientation == ORIENTATION_LANDSCAPE)
	    		ucOrientation = UCOrientation.LANDSCAPE;
	    	UCGameSDK.defaultSDK().setOrientation(ucOrientation);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
