package cn.uc.gamesdk.ane;

import org.json.JSONObject;

import android.util.Log;
import cn.uc.gamesdk.UCCallbackListener;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 进入九游社区
 * 
 */
public class UCFEnterUserCenter implements FREFunction {
	private final static String TAG = "UCFEnterUserCenter";
	
	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFEnterUserCenter calling...");
		try {
			UCGameSDK.defaultSDK().enterUserCenter(context.getActivity().getApplicationContext(), new Listener(context).userCenterListener);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private class Listener {
	    private FREContext context;
        public Listener(FREContext context) {
            this.context = context;
        }
    
        public UCCallbackListener<String> userCenterListener = new UCCallbackListener<String>() {
            @Override
            public void callback(int code, String data) {
            	Log.d(TAG, "Received user center notification: code=" + code + ", data=" + data);
                String msg = null;
                switch (code) {
                case UCGameSDKStatusCode.SUCCESS:
                    msg = "九游社区正常退出";
                    break;
    
                case UCGameSDKStatusCode.NO_INIT:
                    msg = "调用九游社区失败，没有初始化";
                    break;
                case UCGameSDKStatusCode.NO_LOGIN:
                    msg = "调用九游社区失败，没有登录";
                    break;
                default:
                    msg = "";
                    break;
                }
                
                try{
                    JSONObject jobj = new JSONObject();
                    jobj.put("callbackType", "EnterUserCenter");
                    jobj.put("code", code);
                    jobj.put("data", msg);
                    if (context != null) {
                        context.dispatchStatusEventAsync(jobj.toString(), "");
                    } else {
                        Log.d(TAG, "dispatchStatusEventAsync canceled: context is null");
                    }
                } catch (Throwable e) {
                    Log.e(TAG, "", e);
                }
            }
        };
	}

}
