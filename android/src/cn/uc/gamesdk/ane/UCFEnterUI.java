package cn.uc.gamesdk.ane;

import org.json.JSONObject;

import android.util.Log;
import cn.uc.gamesdk.UCCallbackListener;
//import cn.uc.gamesdk.UCCallbackListenerNullException;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 进入某一特定SDK界面
 * 
 * @param business
 *            界面业务参数，用于调用目标业务UI.
 */
public class UCFEnterUI implements FREFunction {
    private final static String TAG = "UCFEnterUI";
    
	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFEnterUI calling...");
    	try {
    		String business = args[0].getAsString();
            UCGameSDK.defaultSDK().enterUI(context.getActivity().getApplicationContext(), business, new Listener(context).enterUIListener);
        //} catch (UCCallbackListenerNullException e) {
        //    e.printStackTrace();
        } catch (Throwable e) {
        	e.printStackTrace();
        }
		return null;
	}
	
	private class Listener {
	    private FREContext context;
	    public Listener(FREContext context) {
	        this.context = context;
	    }
	
    	public UCCallbackListener<String> enterUIListener = new UCCallbackListener<String>() {
            @Override
            public void callback(int statuscode, String data) {
                switch (statuscode) {
                case UCGameSDKStatusCode.SUCCESS:
                    break;
                case UCGameSDKStatusCode.FAIL:
                    break;
                case UCGameSDKStatusCode.NO_INIT:
                    break;
                case UCGameSDKStatusCode.NO_LOGIN:
                    break;
                default:
                    break;
                }
                
                Log.d(TAG, "UCCallbackListener.callback: statuscode = " + statuscode + ", data =" + data);
                
                try{
                    JSONObject jobj = new JSONObject();
                    jobj.put("callbackType", "EnterUI");
                    jobj.put("code", statuscode);
                    jobj.put("data", data);
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
