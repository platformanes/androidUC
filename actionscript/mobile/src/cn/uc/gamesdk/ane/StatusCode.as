package cn.uc.gamesdk.ane
{
	/**
	 * 定义 SDK 方法调用的返回状态码
	 * 
	 */
	public class StatusCode
	{
		/**
		 * 调用成功
		 */
		public static const SUCCESS: int = 0;
		
		/**
		 * 调用失败
		 */
		public static const FAIL: int = -2;
		
		/**
		 * 没有初始化
		 */
		public static const NO_INIT: int = -10;
		
		/**
		 * 没有登录
		 */
		public static const NO_LOGIN: int = -11;
		
		/**
		 * 网咯错误
		 */
		public static const NO_NETWORK: int = -12;
		
		/**
		 * 初始化失败
		 */
		public static const INIT_FAIL: int = -100;
		
		/**
		 * 游戏帐户密码错误导致登录失败
		 */
		public static const LOGIN_GAME_USER_AUTH_FAIL: int = -201;
		/**
		 * 网络原因导致游戏帐户登录失败
		 */
		public static const LOGIN_GAME_USER_NETWORK_FAIL: int = -202;
		/**
		 * 其他原因导致的游戏帐户登录失败
		 */
		public static const LOGIN_GAME_USER_OTHER_FAIL: int = -203;
		
		/**
		 * 获取好友关系失败
		 */
		public static const GETFRINDS_FAIL: int = -300;
		
		/**
		 * 获取用户是否会员时失败
		 */
		public static const VIP_FAIL: int = -400;
		/**
		 * 获取用户会员特权信息时失败
		 */
		public static const VIPINFO_FAIL: int = -401;
		
		/**
		 * 用户退出充值界面
		 */
		public static const PAY_USER_EXIT: int = -500;
		
		/**
		 * 用户退出登录界面
		 */
		public static const LOGIN_EXIT: int = -600;
		
		/**
		 * SDK界面将要显示
		 */
		public static const SDK_OPEN: int = -700;
		
		/**
		 * SDK界面将要关闭，返回到游戏画面
		 */
		public static const SDK_CLOSE: int = -701;
		
		/**
		 * 游客状态
		 */
		public static const GUEST: int = -800;
		
		/**
		 * uc账户登录状态
		 */
		public static const UC_ACCOUNT: int = -801;
		
		/**
		 * 退出游客试玩激活绑定页面回调状态码
		 */
		public static const BIND_EXIT: int = -900;


	}
}