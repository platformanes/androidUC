package
{
	import cn.uc.gamesdk.ane.Constants;
	import cn.uc.gamesdk.ane.GameUserLoginResult;
	import cn.uc.gamesdk.ane.IGameUserLogin;
	import cn.uc.gamesdk.ane.StatusCode;
	
	/**
	 * 实现游戏老账号登录验证的类。如果不需要支持游戏老账号登录，则不需实现该类。 
	 * 
	 */
	public class GameUserLoginOperation implements IGameUserLogin
	{
		public function GameUserLoginOperation()
		{
		}
		
		public function process(username:String, password:String):GameUserLoginResult
		{
			var gulr: GameUserLoginResult = null;
			
			var sid:String;
			var authenticated:Boolean;
			
			//此方法中应将用户名和密码发给游戏服务器进行验证，并从游戏服务器获取sid。
			//下面的逻辑仅为模拟，以用户名和密码相同当作
			authenticated = (username == password);
			if (authenticated) 
			{
				sid = "c7f6675c-21d4-46c7-ae77-7e4e4ee6a344120714";	//要使用从游戏服务器返回的真实的sid
				gulr = new GameUserLoginResult(StatusCode.SUCCESS, sid);
			} else {
				gulr = new GameUserLoginResult(StatusCode.LOGIN_GAME_USER_AUTH_FAIL, "");
			}
			
			return gulr;
		}
		
	}
}