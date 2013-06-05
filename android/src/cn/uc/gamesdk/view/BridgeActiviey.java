package cn.uc.gamesdk.view;


import org.json.JSONObject;

import cn.uc.gamesdk.UCCallbackListener;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;
import cn.uc.gamesdk.info.OrderInfo;
import cn.uc.gamesdk.info.PaymentInfo;

import com.adobe.fre.FREContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Rect
 * @version  Time：2013-5-31 
 */
public class BridgeActiviey extends Activity implements OnClickListener 
{
	//声明开启Activity的Action
	public static final String MYACTIVITY_ACTION = "cn.uc.gamesdk.view.BridgeActiviey";
	private String TAG = "BridgeActiviey";
	private LinearLayout layout;
	public static FREContext _context;
	public static PaymentInfo payInfo;


	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//构建界面
		Log.d(TAG, "---------onCreate-------");
		super.onCreate(savedInstanceState);
		Log.d(TAG, "---------getResourceId--2-----");
		// 隐藏标题栏  
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);  
		// 隐藏状态栏  
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundResource(_context.getResourceId("drawable.bg"));
		this.setContentView(layout);

		TextView textv = new TextView(this);
		String str_2 = "跳转验证！点击任意返回游戏....";
		textv.setText(str_2);
		layout.addView(textv);

		layout.setId(1);
		layout.setOnClickListener(this);
		
		Intent intent = getIntent();
	    Bundle bundle = intent.getExtras();
	    int lunchKey = bundle.getInt("selectKey");
		switch(lunchKey)
		{
		case 0:
			Log.d(TAG, "UCFEnterUserCenter calling...");
			try {
				UCGameSDK.defaultSDK().enterUserCenter(this.getApplicationContext(), new Listener(_context).userCenterListener);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			Log.d(TAG, "paypaypaypaypaypaypay...");
			try {

				UCGameSDK.defaultSDK().pay(_context.getActivity().getApplicationContext(), payInfo, 
						new Listener(_context).orderResultListener);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

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
					if (_context != null) {
						_context.dispatchStatusEventAsync(jobj.toString(), "");
						if(code == UCGameSDKStatusCode.SUCCESS)
						{
							Log.d(TAG, "---SUCCESS-Center---Async--退出-------");
							BridgeActiviey.this.finish();
						}
						
					} else {
						Log.d(TAG, "dispatchStatusEventAsync canceled: context is null");
					}
				} catch (Throwable e) {
					Log.e(TAG, "", e);
				}
			}
		};

		public UCCallbackListener<OrderInfo> orderResultListener = new UCCallbackListener<OrderInfo>() {

			@Override
			public void callback(int code, OrderInfo orderInfo) {
				String text = null;

				switch (code) {
				case UCGameSDKStatusCode.SUCCESS:
					if (orderInfo != null) {
						String orderId = orderInfo.getOrderId();
						float amount = orderInfo.getOrderAmount();
						int payway = orderInfo.getPayWay();
						String paywayName = orderInfo.getPayWayName();

						text = "Order Result: OrderId=" + orderId + ", Amount=" + amount + ", PayWayId=" + payway + ", PayWayName=" + paywayName;
						Log.d(TAG, text);
						text = null;

						//
					} else {
						Log.e(TAG, "Received empty order result");
					}

					break;
				case UCGameSDKStatusCode.NO_INIT:
					text = "Paying failed: no init";
					Log.e(TAG, text);
					break;
				case UCGameSDKStatusCode.PAY_USER_EXIT:
					text = "User exit the paying page, return to game page.";
					Log.d(TAG, text);
					break;
				default:
					text = "Unknown paying result code: code=" + code;
					Log.e(TAG, text);
					break;

				}


				try {
					JSONObject jobj = new JSONObject();
					jobj.put("callbackType", "Pay");
					jobj.put("code", code);

					if (orderInfo == null) {
						jobj.put("data", null);
					} else {
						JSONObject jdata = new JSONObject();
						jdata.put("orderId", orderInfo.getOrderId());
						jdata.put("orderAmount", orderInfo.getOrderAmount());
						jdata.put("payWay", orderInfo.getPayWay());
						jdata.put("payWayName", orderInfo.getPayWayName());

						jobj.put("data", jdata);
					}
					if (context != null) {
						context.dispatchStatusEventAsync(jobj.toString(), "");
						Log.d(TAG, "-----Result--Async--退出-------");
						BridgeActiviey.this.finish(); 
					} else {
						Log.d(TAG, "dispatchStatusEventAsync canceled: context is null");
					}
				} catch (Throwable e) {
					Log.e(TAG, "", e);
				}

				
			}
		};
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case 1:
			Log.d(TAG, "---------退出-------");
			BridgeActiviey.this.finish();
			break;
		case 3:
			break;
		}

	}

	@Override  
	public boolean onKeyDown(int keyCode, KeyEvent event)  
	{  
		if (keyCode == KeyEvent.KEYCODE_BACK )  
		{  
		}  
		return super.onKeyDown(keyCode, event);

	}  
}
