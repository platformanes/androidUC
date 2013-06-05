package cn.uc.gamesdk.ane;


import android.content.Intent;
import android.util.Log;
import cn.uc.gamesdk.view.BridgeActiviey;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class UCFShowFloatButton implements FREFunction {
    private final static String TAG = "UCFShowFloatButton";

    public FREObject call(FREContext context, FREObject[] args) {
        
        Log.d(TAG, "------UCFShowFloatButton---Intent处理-------");
		Intent intent = new Intent(BridgeActiviey.MYACTIVITY_ACTION);
		intent.putExtra("selectKey", 0);
		context.getActivity().startActivityForResult(intent, 0);
		Log.d(TAG, "------UCFShowFloatButton---start处理-------");
		
        return null;
    }


}
