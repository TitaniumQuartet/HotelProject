package tiquartet.common.po.userpo;

import java.io.Serializable;

public class UserPO implements Serializable{
	//用户编号
	private String userId;
	//用户名
	private String userName;
	//用户密码
	private String password;
	//用户类型
	private String userType;
	
	public UserPO(){
		
	}
	
	public UserPO(String userId,String userName,String password,String userType){
		super();
		this.userId=userId;
		this.userName=userName;
		this.password=password;
		this.userType=userType;
	}
	
	public String getuserId(){
		return userId;
	}
	
	public void setuserId(String userId){
		this.userId=userId;
	}
	
	public String getuserName(){
		return userName;
	}
	
	public void setuserName(String userName){
		this.userName=userName;
	}
	
	public String getpassword(){
		return password;
	}
	
	public void setpassword(String password){
		this.password=password;
	}
	
	public String getuserType(){
		return userType;
	}
	
	public void setuserTypr(String userType){
		this.userType=userType;
	}
}
