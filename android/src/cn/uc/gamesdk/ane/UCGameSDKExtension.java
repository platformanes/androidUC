package cn.uc.gamesdk.ane;

//import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class UCGameSDKExtension implements FREExtension {
    //private final static String TAG = "UCGameSDKExtension";
    
	@Override
	public FREContext createContext(String contextType) {
	    //Log.d(TAG, "creating ANE context...");
		FREContext ctx = new UCGameSDKContext();
		//if (ctx != null) 
		//    Log.d(TAG, "succeeded create ANE context");
		return ctx;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void initialize() {
		
	}

}

