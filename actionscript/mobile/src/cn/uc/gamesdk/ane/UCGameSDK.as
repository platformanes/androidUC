package cn.uc.gamesdk.ane
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;

	public class UCGameSDK extends EventDispatcher
	{
		private var _context:ExtensionContext;
		
		//private var _eventTarget: IEventDispatcher;
		private var _gameUserLoginOperation: IGameUserLogin;
		
		public function UCGameSDK(target:IEventDispatcher=null)
		{
			//_eventTarget = target;
			_context = ExtensionContext.createExtensionContext("cn.uc.gamesdk.ane.android", null);
			if (_context==null)
				log("UCGameSDK(.as) constructor: _context is null");
			_context.addEventListener(StatusEvent.STATUS, onStatus);
			
			super(target);
		}
		
		/**
		 *设置日志级别：
		 * @param logLevel
		 *   0=错误信息级别，记录错误日志， 
		 *   1=警告信息级别，记录错误和警告日志， 
		 *   2=调试信息级别，记录错误、警告和调试信息，为最详尽的日志级别。 
		 *   Constants 中定义了用到的常量。
		 * @return 
		 * 
		 */
		public function setLogLevel(logLevel: int):void 
		{
			log("setLogLevel calling");
			if (_context==null)
				log("_context is null");
			_context.call("setLogLevel", logLevel);
		}

		/**
		 * 设置屏幕方向（0=竖屏，1=横屏），默认为竖屏（0）。
		 * @param orientation 屏幕方向，0=竖屏，1=横屏，Constants 中定义了用到的常量。
		 */
		public function setOrientation(orientation: int):void 
		{ 
			_context.call("setOrientation", orientation); 
		}
		
		
		/**
		 * 初始化SDK 
		 * @param debugMode 是否联调模式， false=连接SDK的正式生产环境，true=连接SDK的测试联调环境
		 * @param loglevel 日志级别， 
		 *   0=错误信息级别，记录错误日志， 
		 *   1=警告信息级别，记录错误和警告日志， 
		 *   2=调试信息级别，记录错误、警告和调试信息，为最详尽的日志级别 
		 * @param gameId 游戏ID，该ID由UC游戏中心分配，唯一标识一款游戏
		 * @param cpId 游戏合作商ID，该ID由UC游戏中心分配，唯一标识一个游戏合作商
		 * @param serverId 游戏服务器（游戏分区）标识，由UC游戏中心分配
		 * @param serverName 游戏服务器（游戏分区）名称
		 * @param enablePayHistory 是否启用支付查询功能
		 * @param enableLogout 是否启用用户切换功能
		 * @return 
		 * 
		 */
		public function initSDK(debugMode: Boolean,
								loglevel: int, 
								gameId: int, 
								cpId: int, 
								serverId: int, 
								serverName: String,
								enablePayHistory: Boolean,
								enableLogout: Boolean):void 
		{ 
			log("initSDK calling");
			_context.call("initSDK", debugMode,
				loglevel, 
				gameId, 
				cpId, 
				serverId, 
				serverName,
				enablePayHistory,
				enableLogout); 
		}
		
		/**
		 * 调用SDK的用户登录 
		 * @param enableGameAccount 是否允许使用游戏老账号（游戏自身账号）登录
		 * @param gameAccountTitle 游戏老账号（游戏自身账号）的账号名称，如“三国号”、“风云号”等。
		 *         如果 enableGameAccount 为false，此参数的值设为空字符串即可。
		 * @param gameUserLoginOperation 游戏老账号登录操作对象，如果 enableGameAccount 为false，此参数设为空即可，如果 enableGameAccount 为true，此对象不可为空。
		 * @return 
		 * 
		 */
		public function login(enableGameAccount: Boolean, gameAccountTitle: String, gameUserLoginOperation: IGameUserLogin):void 
		{ 
			this._gameUserLoginOperation = gameUserLoginOperation;
			_context.call("login", enableGameAccount, gameAccountTitle); 
		}
		
		/**
		 * 返回用户登录后的会话标识，此标识会在失效时刷新，游戏在每次需要使用该标识时应从SDK获取 
		 * @return 用户登录会话标识
		 * 
		 */
		public function getSid(): String 
		{ 
			return String(_context.call("getSid")); 
		}
		
		/**
		 * 显示悬浮按钮 
		 * @param x 悬浮按钮显示位置的横坐标，单位：%，支持小数。该参数只支持 0 和 100，分别表示在屏幕最左边或最右边显示悬浮按钮。
		 * @param y 悬浮按钮显示位置的纵坐标，单位：%，支持小数。例如：80，表示悬浮按钮显示的位置距屏幕顶部的距离为屏幕高度的 80% 。
		 * @return 
		 * 
		 */
		public function showFloatButton(x: Number, y: Number):void 
		{ 
			_context.call("showFloatButton", x, y); 
		}
		
		/**
		 * 隐藏悬浮按钮 
		 * @return 
		 * 
		 */
		public function hideFloatButton():void 
		{ 
			_context.call("hideFloatButton"); 
		}
		
		/**
		 * 设置玩家选择的游戏分区及角色信息 
		 * @param zoneName 玩家实际登录的分区名称
		 * @param roleId 角色编号
		 * @param roleName 角色名称
		 * @return 
		 * 
		 */
		public function notifyZone(zoneName: String, roleId: String, roleName: String ):void 
		{ 
			_context.call("notifyZone", zoneName, roleId, roleName); 
		}
		
		/**
		 * 执行充值下单操作，此操作会调出充值界面。 
		 * @param allowContinuousPay 设置是否允许连接充值，true表示在一次充值完成后在充值界面中可以继续下一笔充值，false表示只能进行一笔充值即返回游戏。
		 * @param amount 充值金额。默认为0，如果不设或设为0，充值时用户从充值界面中选择或输入金额；如果设为大于0的值，表示固定充值金额，不允许用户选择或输入其它金额。
		 * @param serverId 当前充值的游戏服务器（分区）标识，此标识即UC分配的游戏服务器ID
		 * @param roleId 当前充值用户在游戏中的角色标识
		 * @param roleName 当前充值用户在游戏中的角色名称
		 * @param grade 当前充值用户在游戏中的角色等级
		 * @param customInfo 充值自定义信息，此信息作为充值订单的附加信息，充值过程中不作任何处理，仅用于游戏设置自助信息，比如游戏自身产生的订单号、玩家角色、游戏模式等。
		 *    如果设置了自定义信息，UC在完成充值后，调用充值结果回调接口向游戏服务器发送充值结果时将会附带此信息，游戏服务器需自行解析自定义信息。
		 *    如果不需设置自定义信息，将此参数置为空字符串即可。
		 * @return 
		 * 
		 */
		public function pay(allowContinuousPay: Boolean, 
							amount: Number,
							serverId: int, 
							roleId: String,
							roleName: String,
							grade: String,
							customInfo: String):void 
		{
			_context.call("pay", allowContinuousPay, amount, serverId, roleId, roleName, grade, customInfo); 
		}
		
		/**
		 * 进入九游社区（用户中心） 
		 * @return 
		 * 
		 */
		public function enterUserCenter():void 
		{ 
			_context.call("enterUserCenter"); 
		}
		
		/**
		 * 进入某一特定SDK界面 
		 * @param business 界面业务参数，用于调用目标业务UI。具体的业务参数请参考SDK开发参考文档。
		 * @return 
		 * 
		 */
		public function enterUI(business: String):void 
		{ 
			_context.call("enterUI", business); 
		}
		
		/**
		 * 提交游戏扩展数据，在登录成功以后可以调用。具体的数据种类和数据内容定义，请参考SDK开发参考文档。 
		 * @param dataType 数据种类
		 * @param dataStr 数据内容，是一个 JSON 字符串。
		 * @return 
		 * 
		 */
		public function submitExtendData(dataType: String, dataStr: String):void 
		{ 
			_context.call("submitExtendData", dataType, dataStr);
		}
		
		/**
		 * 当前登录用户是否UC会员，结果从回调中获取。 
		 * @return 
		 * 
		 */
		public function isUCVip():void 
		{ 
			_context.call("isUCVip"); 
		}
		
		/**
		 * 获取当前登录用户的UC会员信息，包括：状态、有效期、特权等，结果从回调中获取。
		 * @return 
		 * 
		 */
		public function getUCVipInfo():void 
		{ 
			_context.call("getUCVipInfo"); 
		}
		
		public function onStatus(event:StatusEvent):void 
		{
			log("received status event: " + event.toString());
			
			var eventObject:Object = JSON.parse(event.code);
			
			if (eventObject.callbackType == "GameUserAuthentication") {
				if (this._gameUserLoginOperation == null) {
					//throw new e
				} else {
					var userName: String = eventObject.data.userName;
					var password: String = eventObject.data.password;
					var loginResult: GameUserLoginResult = this._gameUserLoginOperation.process(userName, password);
					
					log("setGameUserLoginResult to ANE: loginResultCode=" + loginResult.loginResultCode.toString() + ", sid=" + loginResult.sid);
					_context.call("setGameUserLoginResult", loginResult.loginResultCode, loginResult.sid);
				}
			} else {
				
				//eventObject.callbackType;
				//eventObject.code;
				//eventObject.data;
				var evt: CallbackEvent;
				evt = new CallbackEvent(Constants.EVENT_TYPE_UCGameSDKCallback, eventObject.callbackType, eventObject.code, eventObject.data);
				
				this.dispatchEvent(evt);
			}
		}
		
		
		
		private function log(msg: String):void 
		{
			trace(msg);
		}
	}
}
