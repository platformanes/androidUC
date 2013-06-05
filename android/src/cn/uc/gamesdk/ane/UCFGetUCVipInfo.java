package cn.uc.gamesdk.ane;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import cn.uc.gamesdk.UCCallbackListener;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;
import cn.uc.gamesdk.info.PrivilegeInfo;
import cn.uc.gamesdk.info.VipInfo;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 获取当前登录用户的UC会员信息，包括：状态、有效期、特权等。
 * 
 */
public class UCFGetUCVipInfo implements FREFunction {
    private final static String TAG = "UCFGetUCVipInfo";

    public FREObject call(FREContext context, FREObject[] args) {
        Log.d(TAG, "UCFGetUCVipInfo calling...");
        try {
            UCGameSDK.defaultSDK().getUCVipInfo(context.getActivity().getApplicationContext(), new Listener(context).getUCVipInfolistener);
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

        public UCCallbackListener<VipInfo> getUCVipInfolistener = new UCCallbackListener<VipInfo>() {
            @Override
            public void callback(int statuscode, VipInfo data) {
                String texts = "";
                if (statuscode == UCGameSDKStatusCode.SUCCESS) {
                    //
                } else {
                    // 获取会员特权信息失败,失败的代码包括:
                    // (1)UCGameSDKStatusCode.NO_INIT,未初始化
                    // (2)UCGameSDKStatusCode.NO_LOGIN,未登录
                    // (3)UCGameSDKStatusCode.NO_NETWORK,没有网络
                    System.out.println("获取会员特权信息失败,失败的代码是:" + statuscode);
                    texts += "获取会员特权信息失败,失败的代码是:" + statuscode + "\n";
                    Log.d(TAG, texts);
                }

                try {
                    JSONObject jobj = new JSONObject();
                    jobj.put("callbackType", "GetUCVipInfo");
                    jobj.put("code", statuscode);

                    if (data == null) {
                        jobj.put("data", null);
                    } else {
                        JSONObject jdata = new JSONObject();
                        jdata.put("status", data.getStatus());
                        jdata.put("grade", data.getGrade());
                        jdata.put("validFrom", data.getValidFrom());
                        jdata.put("validTo", data.getValidTo());

                        JSONArray jprivileges = new JSONArray();
                        for (PrivilegeInfo pi : data.getPrivilegeList()) {
                            JSONObject jpi = new JSONObject();
                            jpi.put("enjoy", pi.getEnjoy());
                            jpi.put("pId", pi.getpId());
                            jprivileges.put(jpi);
                        }
                        jdata.put("privilegeList", jprivileges);

                        jobj.put("data", jdata);
                    }
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
