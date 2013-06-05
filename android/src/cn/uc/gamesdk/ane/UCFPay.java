package cn.uc.gamesdk.ane;


import android.content.Intent;
import android.util.Log;
import cn.uc.gamesdk.info.PaymentInfo;
import cn.uc.gamesdk.view.BridgeActiviey;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 调用充值功能
 * 
 * @param allowContinuousPay
 *            是否允许连续充值
 * @param amount
 *            金额
 * @param serverId
 *            服务器ID
 * @param roleId
 *            用户角色ID
 * @param roleName
 *            用户角色名称
 * @param userGrade
 *            用户角色的当前等级
 * @param customInfo
 *            自定义信息
 */
public class UCFPay implements FREFunction {
    private final static String TAG = "UCFPay";

    public FREObject call(FREContext context, FREObject[] args) {
        Log.d(TAG, "UCFPay calling...");
		
        try {
        	BridgeActiviey.payInfo = new PaymentInfo();
        	BridgeActiviey.payInfo.setAllowContinuousPay(args[0].getAsBool());
        	BridgeActiviey.payInfo.setAmount((float) args[1].getAsDouble());
        	BridgeActiviey.payInfo.setServerId(args[2].getAsInt());
        	BridgeActiviey.payInfo.setRoleId(args[3].getAsString());
        	BridgeActiviey.payInfo.setRoleName(args[4].getAsString());
        	BridgeActiviey.payInfo.setGrade(args[5].getAsString());
        	BridgeActiviey.payInfo.setCustomInfo(args[6].getAsString());

          
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        Log.d(TAG, "----UCFPay-----Intent处理-------");
		Intent intent = new Intent(BridgeActiviey.MYACTIVITY_ACTION);
		intent.putExtra("selectKey", 1);
		context.getActivity().startActivityForResult(intent, 0);
		Log.d(TAG, "----UCFPay-----start处理-------");
		
        return null;
    }


    

}
