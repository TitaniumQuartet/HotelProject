package tiquartet.CommonModule.vo;

import tiquartet.CommonModule.util.UserType;

public class UserFilterVO {
	//用户名
	public String username = null;
	//真实姓名
	public String realName = null;
	//会员等级下限
	public int lowerLevel = -1;
	//会员等级上限
	public int upperLevel = -1;
	//用户类型
	public UserType userType;
	
	/**
	 * 传入所有成员的构造函数.
	 * @param username
	 * @param realName
	 * @param lowerLevel
	 * @param upperLevel
	 */
	public UserFilterVO(String username, String realName, int lowerLevel,
			int upperLevel) {
		super();
		this.username = username;
		this.realName = realName;
		this.lowerLevel = lowerLevel;
		this.upperLevel = upperLevel;
	}
}
