package cn.uc.gamesdk.ane;

import android.util.Log;
import cn.uc.gamesdk.UCGameSDK;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 提交玩家选择的游戏分区及角色信息
 * 
 * @param zoneName
 *            玩家实际登录的分区名称
 * @param roleId
 *            角色编号
 * @param roleName
 *            角色名称
 */
public class UCFNotifyZone implements FREFunction {
    private final static String TAG = "UCFNotifyZone";
    
	public FREObject call(FREContext context, FREObject[] args) {
	    Log.d(TAG, "UCFNotifyZone calling...");
		try {
			String zoneName = args[0].getAsString(); 
			String roleId = args[1].getAsString();
			String roleName = args[2].getAsString();
			UCGameSDK.defaultSDK().notifyZone(zoneName, roleId, roleName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
