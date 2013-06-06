package cn.uc.gamesdk.ane
{
	/**
	 * 游戏账号登录操作接口，游戏中需要实现此接口完成游戏老账号登录。
	 * 
	 */
	public interface IGameUserLogin
	{
		/**
		 * 游戏老账号登录处理，游戏应在此方法中实现向游戏服务器请求用户名和密码验证，并获得验证结果。
		 * 
		 * @param userName 游戏老账号用户名
		 * @param password 游戏老账号用户密码
		 * @return 登录结果
		 */
		function process(username:String,  password:String):GameUserLoginResult ;
	}
}