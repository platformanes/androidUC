package cn.uc.gamesdk.ane;

import org.json.JSONObject;

import android.util.Log;
import cn.uc.gamesdk.UCCallbackListener;
//import cn.uc.gamesdk.UCCallbackListenerNullException;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;
import cn.uc.gamesdk.UCLogLevel;
import cn.uc.gamesdk.UCOrientation;
import cn.uc.gamesdk.info.ExInfo;
import cn.uc.gamesdk.info.FeatureSwitch;
import cn.uc.gamesdk.info.GameParamInfo;
import cn.uc.gamesdk.view.BridgeActiviey;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class UCFInitSDK implements FREFunction {
    private final static String TAG = "UCFInitSDK";

    // 错误信息级别，记录错误日志
    private final static int LOGLEVEL_ERROR = 0;
    // 警告信息级别，记录错误和警告日志
    private final static int LOGLEVEL_WARN = 1;
    // 调试信息级别，记录错误、警告和调试信息，为最详尽的日志级别
    @SuppressWarnings("unused")
    private final static int LOGLEVEL_DEBUG = 2;


    public FREObject call(FREContext context, FREObject[] args) {
        Log.d(TAG, "UCFInitSDK calling...");
        try {
            boolean debugMode = args[0].getAsBool();
            int loglevel = args[1].getAsInt();
            int gameId = args[2].getAsInt();
            int cpId = args[3].getAsInt();
            int serverId = args[4].getAsInt();
            String serverName = args[5].getAsString();
            boolean enablePayHistory = args[6].getAsBool();
            boolean enableLogout = args[7].getAsBool();
            Log.d(TAG, "initSDK parameters: debugMode=" + debugMode + ", loglevel=" + loglevel + ", gameId=" + gameId + ", cpId=" + cpId 
                    + ", serverId=" + serverId + ", serverName=" + serverName + ", enablePayHistory=" + enablePayHistory + ", enableLogout=" + enableLogout);

            BridgeActiviey._context = context;
            GameParamInfo gp = new GameParamInfo();Log.d(TAG, "after gp newed");
            gp.setFeatureSwitch(new FeatureSwitch(enablePayHistory, enableLogout));
            gp.setGameId(gameId);
            gp.setCpId(cpId);
            gp.setServerId(serverId);
            gp.setServerName(serverName);
            Log.d(TAG, "after gp values set");

            ExInfo ex = new ExInfo();
            //ex.setCpServiceContact("");
            gp.setExInfo(ex);
            Log.d(TAG, "after ex set into gp");

            UCLogLevel ucloglevel = UCLogLevel.DEBUG;
            if (loglevel == LOGLEVEL_ERROR)
                ucloglevel = UCLogLevel.ERROR;
            else if (loglevel == LOGLEVEL_WARN)
                ucloglevel = UCLogLevel.WARN;

            Log.d(TAG, "will init SDK...");
            UCGameSDK.defaultSDK().initSDK(context.getActivity().getApplicationContext(), ucloglevel, debugMode, gp, new Listener(context).initResultListener);
            Log.d(TAG, "after init SDK invoked");
            
            UCGameSDK.defaultSDK().setLogoutNotifyListener(new UCFLogin.LogoutListener(context).logoutListener);
            Log.d(TAG, "after setLogoutNotifyListener");
            UCGameSDK.defaultSDK().setOrientation(UCOrientation.LANDSCAPE);
            Log.d(TAG, "UCFInitSDK 设置横屏");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private static class Listener {
        private FREContext context;

        public Listener(FREContext context) {
            this.context = context;
        }

        private UCCallbackListener<String> initResultListener = new UCCallbackListener<String>() {
            public void callback(int code, String msg) {
                Log.d(TAG, "UCGameSDK init result: code=" + code + ", msg=" + msg + "");
                switch (code) {
                case UCGameSDKStatusCode.SUCCESS:
                    // 初始化成功
                    break;
                case UCGameSDKStatusCode.INIT_FAIL:
                    break;
                case UCGameSDKStatusCode.LOGIN_EXIT:
                    //addOutputResult("login-exit", String.valueOf(code));
                    break;
                default:
                    //addOutputResult("login-fail", String.valueOf(code));
                    break;
                }

                try {
                    JSONObject jobj = new JSONObject();
                    jobj.put("callbackType", "InitSDK");
                    jobj.put("code", code);
                    jobj.put("data", msg);
                    if (context != null) {
                        Log.d(TAG, "dispatch init result event to AIR context.");
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
