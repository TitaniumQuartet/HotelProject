package tiquartet.ServerModule.po;

import java.io.Serializable;
import java.sql.Date;

import tiquartet.CommonModule.vo.UserVO;

public class UserPO implements Serializable{
	//鐢ㄦ埛缂栧彿
	private int userId;
	//鐢ㄦ埛鍚�
	private String userName;
	//瀵嗙爜
	private String password;
	//鐢ㄦ埛绫诲瀷
	private String userType;
	//鐪熷疄濮撳悕
	private String realName;
	//淇＄敤鍊�
	private int credit;
	//鐢熸棩
	private Date birthday;
	//浼氬憳绛夌骇
	private int memberRank;
	//鏄惁浼氬憳
	private boolean isMember;
	//鍏徃鍚嶇О
	private String company;
	//閰掑簵缂栧彿
	private int hotelId;
	
	public UserPO(){
		
	}
	
	public UserPO(int userId,String userName,String password,String userType,String realName,int credit, Date birthday,int memberRank,boolean isMember,String company,int hotelId){
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
