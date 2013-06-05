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
 * 当前登录用户是否UC会员
 * 
 */
public class UCFIsUCVip implements FREFunction {
    private final static String TAG = "UCFIsUCVip";

    public FREObject call(FREContext context, FREObject[] args) {
        Log.d(TAG, "UCFIsUCVip calling...");
        try {
            UCGameSDK.defaultSDK().isUCVip(context.getActivity().getApplicationContext(), new Listener(context).isUCVipListener);
        //} catch (UCCallbackListenerNullException e) {
        //    e.printStackTrace();
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

        private UCCallbackListener<Boolean> isUCVipListener = new UCCallbackListener<Boolean>() {
            @Override
            public void callback(int statuscode, Boolean data) {
                String texts = "";
                if (statuscode == UCGameSDKStatusCode.SUCCESS) {
                    // 表明成功获取了是否会员的信息
                    texts += "current user vip status is :" + data + "\n";
                    Log.d(TAG, texts);
                } else {
                    // 获取是否会员信息失败,失败的代码包括:
                    // (1)UCGameSDKStatusCode.NO_INIT,未初始化
                    // (2)UCGameSDKStatusCode.NO_LOGIN,未登录
                    // (3)UCGameSDKStatusCode.NO_NETWORK,没有网络
                    System.out.println("获取是否会员信息失败,失败的代码是:" + statuscode);
                    texts += "获取是否会员信息失败,失败的代码是:" + statuscode + "\n";
                    Log.d(TAG, texts);
                }

                try {
                    JSONObject jobj = new JSONObject();
                    jobj.put("callbackType", "IsUCVip");
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
