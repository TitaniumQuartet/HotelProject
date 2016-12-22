package tiquartet.CommonModule.vo;

import java.io.Serializable;

import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.UserType;

public class UserFilterVO implements Serializable{
	//用户名
	public String username = "";
	//真实姓名
	public String realName = "";
	//用户类型
	public UserType userType;
	//会员类型
	public MemberType memberType;
	
	/**
	 * 传入所有成员的构造函数.
	 * @param username
	 * @param realName
	 * @param lowerLevel
	 * @param upperLevel
	 */
	public UserFilterVO(String username, String realName, UserType userType, MemberType memberType) {
		super();
		this.username = username;
		this.realName = realName;
		this.userType = userType;
		this.memberType = memberType;
	}
}
