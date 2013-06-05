package cn.uc.gamesdk.ane;

import org.json.JSONObject;

import android.util.Log;
import cn.uc.gamesdk.GameUserLoginResult;
import cn.uc.gamesdk.IGameUserLogin;
import cn.uc.gamesdk.UCCallbackListener;
//import cn.uc.gamesdk.UCCallbackListenerNullException;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class UCFLogin implements FREFunction {
    private final static String TAG = "UCFLogin";
    
    public static Object gameUserLoginSync = null;
    public static boolean gotGameUserAuthenticationResult = false;
    public static int gameUserLoginResultCode;
    public static String gameuserLoginResultSid;

    public FREObject call(FREContext context, FREObject[] args) {
        Log.d(TAG, "UCFLogin calling...");
        try {
            boolean enableGameAccount = args[0].getAsBool();
            String gameAccountTitle = args[1].getAsString();
            if (enableGameAccount) {
                UCGameSDK.defaultSDK().login(context.getActivity().getApplicationContext(), new LoginListener(context).loginResultListener, 
                        new GameUserLoginListener(context).gameUserLoginOperation, gameAccountTitle);
            } else {
                UCGameSDK.defaultSDK().login(context.getActivity().getApplicationContext(), new LoginListener(context).loginResultListener);
            }
        //} catch (UCCallbackListenerNullException e) {
        //    e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static class GameUserLoginListener {
        private FREContext context;

        public GameUserLoginListener(FREContext context) {
            this.context = context;
        }

        private IGameUserLogin gameUserLoginOperation = new IGameUserLogin() {
            @Override
            public GameUserLoginResult process(String username, String password) {
                gameUserLoginSync = new Object();
                gotGameUserAuthenticationResult = false;
                
                GameUserLoginResult gulr = new GameUserLoginResult();

                try {
                    synchronized (gameUserLoginSync) {
                        JSONObject jobj = new JSONObject();
                        jobj.put("callbackType", "GameUserAuthentication");
                        jobj.put("code", 1786823);
                        JSONObject jdata = new JSONObject();
                        jdata.put("userName", username);
                        jdata.put("password", password);
                        jobj.put("data", jdata);
    
                        if (context != null) {
                            context.dispatchStatusEventAsync(jobj.toString(), "");
                        } else {
                            Log.d(TAG, "dispatchStatusEventAsync canceled: context is null");
                        }
    
                        if (!gotGameUserAuthenticationResult) {
                            Log.d(TAG, "wait for game user authentication result");
                            gameUserLoginSync.wait(1000 * 60 * 2);
                        }
                    
                        if (gotGameUserAuthenticationResult) {
                            Log.d(TAG, "got GameUserAuthenticationResult");
                            gulr.setLoginResult(gameUserLoginResultCode);
                            gulr.setSid(gameuserLoginResultSid);
                        } else {
                            Log.d(TAG, "did not received GameUserAuthenticationResult!");
                            gulr.setLoginResult(UCGameSDKStatusCode.LOGIN_GAME_USER_OTHER_FAIL);
                            gulr.setSid("");
                        }
                    }
    
                } catch (Throwable e) {
                    Log.e(TAG, "", e);
                    gulr.setLoginResult(UCGameSDKStatusCode.LOGIN_GAME_USER_OTHER_FAIL);
                    gulr.setSid("");
                }

                return gulr;
            }
        };
    }

    private static class LoginListener {
        private FREContext context;

        public LoginListener(FREContext context) {
            this.context = context;
        }

        private UCCallbackListener<String> loginResultListener = new UCCallbackListener<String>() {

            @Override
            public void callback(int code, String msg) {
                Log.d(TAG, "UCGameSDK login result: code=" + code + ", msg=" + msg);

                switch (code) {
                case UCGameSDKStatusCode.SUCCESS:
                    //String sid = UCGameSDK.defaultSDK().getSid();
                    break;
                case UCGameSDKStatusCode.NO_INIT:
                    break;
                case UCGameSDKStatusCode.LOGIN_EXIT:
                    break;
                default:
                    break;

                }

                try {
                    JSONObject jobj = new JSONObject();
                    jobj.put("callbackType", "Login");
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

    static class LogoutListener {
        private FREContext context;

        public LogoutListener(FREContext context) {
            this.context = context;
        }

        UCCallbackListener<String> logoutListener = new UCCallbackListener<String>() {

            @Override
            public void callback(int code, String msg) {
                Log.d(TAG, "received logout notification: msg=" + msg);

                try {
                    JSONObject jobj = new JSONObject();
                    jobj.put("callbackType", "Logout");
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
