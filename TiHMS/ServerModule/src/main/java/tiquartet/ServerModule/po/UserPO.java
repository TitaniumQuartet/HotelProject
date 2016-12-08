package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.util.UserType;
import tiquartet.CommonModule.vo.UserVO;

public class UserPO implements Serializable{
	//用户编号
	private int userId;
	//用户名
	private String userName;
	//密码
	private String password;
	//用户类型
	private UserType userType;
	//用户真实姓名
	private String realName;
	//当前信用值
	private double credit;
	//生日
	private String birthday;
	//会员等级
	private int memberLevel;
	//是否会员
	private boolean isMember;
	//公司名称（用户为会员）
	private String company;
	//酒店编号（用户为酒店工作人员时）
	private int hotelId;
	//是否已登录
	private boolean login;
	
	public UserPO(){
		
	}
	
	public UserPO(int userId,String userName,String password,UserType userType,String realName,double credit, String birthday,int memberRank,boolean isMember,String company,int hotelId,boolean login){
		super();
		this.userId=userId;
		this.userName=userName;
		this.password=password;
		this.userType=userType;
		this.realName=realName;
		this.credit=credit;
		this.birthday=birthday;
		this.memberLevel=memberRank;
		this.isMember=isMember;
		this.company=company;
		this.hotelId=hotelId;
	}
	
	public UserPO(UserVO userVO){
		super();
		this.userId=userVO.userID;
		this.userName=userVO.userName;
		this.password=userVO.password;
		this.userType=userVO.userType;
		this.realName=userVO.realName;
		this.credit=userVO.credit;
		this.birthday=userVO.birthday;
		this.memberLevel=userVO.memberLevel;
		//this.isMember=userVO.isMember;
		this.company=userVO.company;
		this.hotelId=userVO.hotelID;
		this.login=userVO.login;
	}
	
	public int getuserId(){
		return userId;
	}
	
	public void setuserId(int userId){
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
	
	public UserType getuserType(){
		return userType;
	}
	
	public void setuserType(UserType userType){
		this.userType=userType;
	}
	
	/**
	 * 得到枚举类型的序号以便在数据库中存储.
	 * @return
	 */
	public int getTypeAsInt(){
		return userType.ordinal();
	}
	
	/**
	 * 传入枚举类型序号的setter.
	 * @param typeOrdinal
	 */
	public void setTypeAsInt(int typeOrdinal){
		this.userType = UserType.values()[typeOrdinal];
	}
	
	public void setrealName(String realName){
		this.realName=realName;
	}
	
	public String getrealName(){
		return this.realName;
	}
	
	public void setbirthday(String birthday){
		this.birthday=birthday;
	}
	
	public String getbirthday(){
		return this.birthday;
	}
	
	public void setcredit(double credit){
		this.credit=credit;
	}
	
	public double getcredit(){
		return this.credit;
	}
	
	public void setmemberRank(int memberRank){
		this.memberLevel=memberRank;
	}
	
	public int getmemberRank(){
		return this.memberLevel ;
	}
	
	public void setisMember(boolean isMember){
		this.isMember=isMember;
	}
	
	public boolean getisMember(){
		return this.isMember;
	}
	
	public void setcompany(String company){
		this.company=company;
	}
	
	public String getcompany(){
		return this.company;
	}
	
	public void sethotelId(int hotelId){
		this.hotelId=hotelId;
	}
	
	public int gethotelId(){
		return this.hotelId;
	}
	
	public boolean getLogin(){
		return this.login;
	}
	
	public void setLogin(boolean login){
		this.login = login;
	}
	
	public UserVO getVO(){
		UserVO userVO = new UserVO();
		userVO.password = this.password;
		userVO.realName = this.realName;
		userVO.userID = this.userId;
		userVO.userName = this.userName;
		userVO.userType = this.userType;
		userVO.login = this.login;
		
		return userVO;
	}
}
