package cn.uc.gamesdk.ane;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 
 */
public class UCFSetGameUserLoginResult implements FREFunction {
    private final static String TAG = "UCFSetGameUserLoginResult";
	
	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFSetGameUserLoginResult calling...");
		try {
		    synchronized (UCFLogin.gameUserLoginSync) {
    			int loginResultCode = args[0].getAsInt();
    			String sid = args[1].getAsString();
    			Log.d(TAG, "received login result from game: loginResultCode=" + loginResultCode + ", sid=" + sid);
    			
    			UCFLogin.gameUserLoginResultCode = loginResultCode;
    			UCFLogin.gameuserLoginResultSid = sid;
    			UCFLogin.gotGameUserAuthenticationResult = true;
    			UCFLogin.gameUserLoginSync.notify();
		    }
	        
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage(), e);
		}
		return null;
	}
	
	
		
}
