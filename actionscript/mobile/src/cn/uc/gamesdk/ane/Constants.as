package cn.uc.gamesdk.ane
{
	public class Constants
	{
		//错误信息级别，记录错误日志
		public static const LOGLEVEL_ERROR:int = 0;	
		//警告信息级别，记录错误和警告日志
		public static const LOGLEVEL_WARN:int = 1;
		//调试信息级别，记录错误、警告和调试信息，为最详尽的日志级别
		public static const LOGLEVEL_DEBUG:int = 2;
		
		
		//竖屏
		public static const ORIENTATION_PORTRAIT:int = 0;
		//横屏
		public static const ORIENTATION_LANDSCAPE:int = 1;
		
		
		public static const EVENT_TYPE_UCGameSDKCallback:String	= "EVENT_TYPE_UCGameSDKCallback";
		
		//定义回调事件类型
		public static const CALLBACKTYE_InitSDK:String 			= "InitSDK";
		public static const CALLBACKTYE_Login:String 				= "Login";
		public static const CALLBACKTYE_Logout:String 			= "Logout";
		public static const CALLBACKTYE_ShowFloatButton:String 	= "ShowFloatButton";
		public static const CALLBACKTYE_EnterUserCenter:String 	= "EnterUserCenter";
		public static const CALLBACKTYE_EnterUI:String 			= "EnterUI";
		public static const CALLBACKTYE_Pay:String 				= "Pay";
		public static const CALLBACKTYE_IsUCVip:String 			= "IsUCVip";
		public static const CALLBACKTYE_GetUCVipInfo:String 		= "GetUCVipInfo";
		
	}
}