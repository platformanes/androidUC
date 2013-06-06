package cn.uc.gamesdk.ane
{
	public class GameUserLoginResult
	{
		/**
		 * 登录结果状态码，可以选择的值定义在 StatusCode 中，包括：<br>
		 * (1)SUCCESS ：登录成功<br>
		 * (2)LOGIN_GAME_USER_AUTH_FAIL ：游戏帐户密码错误导致登录失败<br>
		 * (3)LOGIN_GAME_USER_NETWORK_FAIL ：网络原因导致游戏帐户登录失败<br>
		 * (4)LOGIN_GAME_USER_OTHER_FAIL ：其他原因导致的游戏帐户登录失败
		 */
		private var _loginResultCode:int;
		
		/**
		 * 登录成功返回的sid
		 */
		private var _sid:String;
		
		
		/**
		 * 构造函数
		 * @param loginResult 登录结果状态码，可选的值如 StatusCode.SUCCESS, StatusCode.LOGIN_GAME_USER_* 等
		 * @param sid 登录成功得到的sid
		 * 
		 */
		public function GameUserLoginResult(loginResultCode:int, sid:String)
		{
			this._loginResultCode = loginResultCode;
			this._sid = sid;
		}
		
		public function set loginResultCode(value:int):void {
			this._loginResultCode = value;
		}
		
		public function get loginResultCode():int {
			return this._loginResultCode;
		}
		
		public function set sid(sid:String):void {
			this._sid = sid;
		}
		
		public function get sid():String {
			return this._sid;
		}
	}
}