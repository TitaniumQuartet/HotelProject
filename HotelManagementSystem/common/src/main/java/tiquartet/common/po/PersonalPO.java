package tiquartet.common.po;

import java.io.Serializable;
import java.util.Calendar;

public class PersonalPO implements Serializable{
	//用户编号
	private String userId;
	//用户名
	private String userName;
	//用户密码
	private String password;
	//用户类型
	private String userType;
	//是否会员
	private boolean ismember;
	//信用值
	private int credit;
	//生日
	private Calendar birthday;
	//公司
	private String company;
	
	public PersonalPO(){
		
	}
	
	public PersonalPO(String userId,String userName,String password,String userType,boolean ismember,int credit,Calendar birthday,String company){
		super();
		this.userId=userId;
		this.userName=userName;
		this.password=password;
		this.userType=userType;
		this.ismember=ismember;
		this.credit=credit;
		this.birthday=birthday;
		this.company=company;
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
	
	public boolean getismember(){
		return ismember;
	}
	
	public void setismember(boolean ismember){
		this.ismember=ismember;
	}
	
	public int getcredit(){
		return credit;
	}
	
	public void setcredit(int credit){
		this.credit=credit;
	}
	
	public Calendar getbirthday(){
		return birthday;
	}
	
	public void setbirthday(Calendar birthday){
		this.birthday=birthday;
	}
	
	public String getcompany(){
		return userType;
	}
	
	public void setcompany(String company){
		this.company=company;
	}
}
