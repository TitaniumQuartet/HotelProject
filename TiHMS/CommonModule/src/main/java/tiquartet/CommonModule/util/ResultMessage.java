package tiquartet.CommonModule.util;

import java.io.Serializable;

/**
 * 用于包装一次方法调用的结果及相关信息.
 * 
 * @author greatlyr
 *
 */
public class ResultMessage implements Serializable {
	
	// 方法调用是否成功
	public boolean result;
	// 操作失败的原因
	public String failInfo;
	// 额外要传递的信息
	public String message;

	/**
	 * 仅指定操作是否成功的构造函数.
	 * 
	 * @param isSuccessful
	 */
	public ResultMessage(boolean isSuccessful) {
		result = isSuccessful;
		failInfo = null;
		message = null;
	}

	/**
	 * 指定操作结果、失败原因、消息的构造函数.
	 * 
	 * @param isSuccessful
	 * @param failInfo
	 * @param message
	 */
	public ResultMessage(boolean isSuccessful, String failInfo,
			String message) {
		result = isSuccessful;
		this.failInfo = failInfo;
		this.message = message;
	}
	
}
