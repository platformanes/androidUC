package cn.uc.gamesdk.ane;

import org.json.JSONObject;

import android.util.Log;
import cn.uc.gamesdk.UCGameSDK;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 提交游戏扩展数据，在登录成功以后可以调用。具体的数据种类和数据内容定义，请参考“开发参考说明书”。
 * 
 * @param type
 *            数据种类
 * @param data
 *            数据内容，JSON对象。
 */
public class UCFSubmitExtendData implements FREFunction {
    private final static String TAG = "UCFSubmitExtendData"; 
    
	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFSubmitExtendData calling...");
		try {
			String dataType = args[0].getAsString();
			String dataStr = args[1].getAsString();
			JSONObject data = new JSONObject(dataStr); 
			
			 UCGameSDK.defaultSDK().submitExtendData(dataType, data);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage(), e);
		}
		return null;
	}
	

}
