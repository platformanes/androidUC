package cn.uc.gamesdk.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class UCGameSDKContext extends FREContext {

	@Override
	public void dispose() {
		
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		
        functionMap.put("setLogLevel", new UCFSetLogLevel());
        functionMap.put("setOrientation", new UCFSetOrientation());
        functionMap.put("initSDK", new UCFInitSDK());
        functionMap.put("setGameUserLoginResult", new UCFSetGameUserLoginResult());
        functionMap.put("login", new UCFLogin());
        functionMap.put("getSid", new UCFGetSid());
        functionMap.put("showFloatButton", new UCFShowFloatButton());
        functionMap.put("hideFloatButton", new UCFHideFloatButton());
        functionMap.put("notifyZone", new UCFNotifyZone());
        functionMap.put("pay", new UCFPay());
        functionMap.put("enterUserCenter", new UCFEnterUserCenter());
        functionMap.put("enterUI", new UCFEnterUI());
        functionMap.put("submitExtendData", new UCFSubmitExtendData());
        functionMap.put("isUCVip", new UCFIsUCVip());
        functionMap.put("getUCVipInfo", new UCFGetUCVipInfo());
		
		return functionMap;
	}

}

