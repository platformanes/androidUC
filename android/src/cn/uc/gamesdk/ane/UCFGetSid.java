package cn.uc.gamesdk.ane;

import android.util.Log;
import cn.uc.gamesdk.UCGameSDK;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class UCFGetSid implements FREFunction {
    private final static String TAG = "UCFGetSid";
    
	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFGetSid calling...");
		try {
			return FREObject.newObject(UCGameSDK.defaultSDK().getSid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
