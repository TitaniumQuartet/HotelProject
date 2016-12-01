package tiquartet.ServerModule.po;

import java.io.Serializable;
import java.sql.Date;

import tiquartet.CommonModule.vo.UserVO;

public class UserPO implements Serializable{
	//用户编号
	private int userId;
	//用户名
	private String userName;
	//密码
	private String password;
	//用户类型
	private int userType;
	//真实姓名
	private String realName;
	//信用值
	private int credit;
	//生日
	private Date birthday;
	//会员等级
	private int memberRank;
	//是否会员
	private boolean isMember;
	//公司名称
	private String company;
	//酒店编号
	private int hotelId;
	
	public UserPO(){
		
	}
	
	public UserPO(int userId,String userName,String password,int userType,String realName,int credit, Date birthday,int memberRank,boolean isMember,String company,int hotelId){
		super();
		this.userId=userId;
		this.userName=userName;
		this.password=password;
		this.userType=userType;
		this.realName=realName;
		this.credit=credit;
		this.birthday=birthday;
		this.memberRank=memberRank;
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
		this.memberRank=userVO.memberRank;
		this.isMember=userVO.isMember;
		this.company=userVO.company;
		this.hotelId=userVO.hotelID;
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
	
	public int getuserType(){
		return userType;
	}
	
	public void setuserType(int userType){
		this.userType=userType;
	}
	
	public void setrealName(String realName){
		this.realName=realName;
	}
	
	public String getrealName(){
		return this.realName;
	}
	
	public void setbirthday(Date birthday){
		this.birthday=birthday;
	}
	
	public Date getbirthday(){
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
	
	public UserVO getVO(){
		UserVO userVO = new UserVO();
		userVO.password = this.password;
		userVO.realName = this.realName;
		userVO.userID = this.userId;
		userVO.userName = this.userName;
		userVO.userType = this.userType;
		
		return userVO;
	}
}
