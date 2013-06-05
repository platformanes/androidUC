package cn.uc.gamesdk.ane;

import android.util.Log;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCLogLevel;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 设置日志级别：
 * 0=错误信息级别，记录错误日志， 
 * 1=警告信息级别，记录错误和警告日志， 
 * 2=调试信息级别，记录错误、警告和调试信息，为最详尽的日志级别
 */
public class UCFSetLogLevel implements FREFunction {
    private final static String TAG = "UCFSetLogLevel";
    
	//错误信息级别，记录错误日志
	public final static int LOGLEVEL_ERROR = 0;	
	//警告信息级别，记录错误和警告日志
	public final static int LOGLEVEL_WARN = 1;
	//调试信息级别，记录错误、警告和调试信息，为最详尽的日志级别
	public final static int LOGLEVEL_DEBUG = 2;
	
	
	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFSetLogLevel calling...");
		try {
			int loglevel = args[0].getAsInt();
			UCLogLevel ucloglevel = UCLogLevel.DEBUG;
			if (loglevel == LOGLEVEL_ERROR)
				ucloglevel = UCLogLevel.ERROR;
			else if (loglevel == LOGLEVEL_WARN)
				ucloglevel = UCLogLevel.WARN;
			else
				ucloglevel = UCLogLevel.DEBUG;
	        UCGameSDK.defaultSDK().setLogLevel(ucloglevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
		
}
