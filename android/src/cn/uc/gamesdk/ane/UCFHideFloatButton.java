package cn.uc.gamesdk.ane;

import android.util.Log;
import cn.uc.gamesdk.UCGameSDK;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class UCFHideFloatButton implements FREFunction {
    private final static String TAG = "UCFHideFloatButton";
    
	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFHideFloatButton calling...");
		try {
			UCGameSDK.defaultSDK().showFloatButton(context.getActivity(), 0, 0, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
