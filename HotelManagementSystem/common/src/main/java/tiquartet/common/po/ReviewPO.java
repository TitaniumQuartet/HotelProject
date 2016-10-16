package tiquartet.common.po;

import java.io.Serializable;
import java.util.Calendar;


public class ReviewPO implements Serializable{
	//酒店编号
	private String hotelId;
	//评分
	private int score;
	//评价内容
	private String review;
	//用户编号
	private int userId;
	//用户名
	private String userName;
	//评价时间
	private Calendar time;
	
	public ReviewPO(){
		
	}
	
	public ReviewPO(String hotelId,int score,String review,int userId,String userName,Calendar time){
		super();
		this.hotelId=hotelId;
		this.score=score;
		this.review=review;
		this.userId=userId;
		this.userName=userName;
		this.time=time;
	}
	
	public String gethotelId(){
		return hotelId;
	}
	
	public void sethotelId(String hotelId){
		this.hotelId=hotelId;
	}
	
	public int getscore(){
		return score;
	}
	
	public void setscore(int score){
		this.score=score;
	}
	
	public String getreview(){
		return review;
	}
	
	public void setreview(String review){
		this.review=review;
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
	
	public Calendar gettime(){
		return time;
	}
	
	public void settime(Calendar time){
		this.time=time;
	}
	
}