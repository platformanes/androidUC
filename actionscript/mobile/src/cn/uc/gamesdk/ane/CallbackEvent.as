package cn.uc.gamesdk.ane
{
	import flash.events.Event;
	import flash.events.StatusEvent;
	
	/**
	 * 此类定义SDK向游戏返回的异步调用的结果。
	 * 不同的回调事件类型有不同的状态码和不同的结果数据，游戏应根据回调事件类型读取相应的状态码和结果数据，根据回调事件类型和状态码进行相应的逻辑处理。
	 * 
	 */
	public class CallbackEvent extends Event
	{
		private var _eventType: String;
		private var _callbackType: String;
		private var _code: int;
		private var _data: Object;
		
		public function CallbackEvent(eventType:String, callbackType: String, code: int, data: Object, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			this._eventType = eventType;
			this._callbackType = callbackType;
			this._code = code;
			this._data = data;
			super(eventType, bubbles, cancelable);
		}
		
		/**
		 * 获取回调事件类型，Constants 中定义了回调事件类型常量（CALLBACKTYE_*），游戏应根据此回调事件类型对事件进行不同的处理。
		 * @return 
		 * 
		 */
		public function get callbackType(): String {
			return _callbackType;
		}
		
		/**
		 * 获取回调事件状态码，表示SDK返回的执行结果和状态，StatusCode 中定义了回调事件状态码常量。不同回调事件类型的事件有自己对应的状态列表，游戏应根据此状态码进行不同的处理。 
		 * @return 
		 * 
		 */
		public function get code(): int {
			return _code;
		}
		
		/**
		 * 获取SDK返回的执行结果数据，不同回调事件类型的事件具有不同的数据结构，游戏应根据回调事件类型来获取相应的数据。 
		 * @return 
		 * 
		 */
		public function get data(): Object {
			return _data;
		}
	}
}