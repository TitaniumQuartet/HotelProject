package tiquartet.ServerModule.po;

import java.io.Serializable;
import java.util.Calendar;

public class UserPO implements Serializable{
	//用户编号
	private String userId;
	//用户名
	private String userName;
	//用户密码
	private String password;
	//用户类型
	private String userType;
	//用户真实名字
	private String realName;
	//用户信用值
	private int credit;
	//用户生日
	private Calendar birthday;
	//会员等级
	private int memberRank;
	
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
	
	public void setuserType(String userType){
		this.userType=userType;
	}
	
	public void setrealName(String realName){
		this.realName=realName;
	}
	
	public String getrealName(){
		return this.realName;
	}
	
	public void setbirthday(Calendar birthday){
		this.birthday=birthday;
	}
	
	public Calendar getbirthday(){
		return this.birthday;
	}
	
	public void setcredit(int credit){
		this.credit=credit;
	}
	
	public int getcredit(){
		return this.credit;
	}
	
	public void setmemberRank(int memberRank){
		this.memberRank=memberRank;
	}
	
	public int getmemberRank(){
		return this.memberRank ;
	}
}
