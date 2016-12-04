package tiquartet.ServerModule.po;

import java.io.Serializable;
import java.util.Calendar;

import tiquartet.CommonModule.vo.ReviewVO;


public class ReviewPO implements Serializable{
	//酒店编号
	private int hotelId;
	//评分
	private int score;
	//评价内容
	private String review;
	//用户编号
	private int userId;
	//用户名
	private String userName;
	//评价时间
	private String time;
	
	public ReviewPO(ReviewVO reviewvo){
		this.hotelId=reviewvo.hotelID;
		this.review=reviewvo.review;
		this.score=reviewvo.score;
		this.time=reviewvo.time;
		this.userId=reviewvo.userID;
		this.userName=reviewvo.userName;
	}
	public ReviewPO(){
		
	}
	public ReviewPO(int hotelId,int score,String review,int userId,String userName,String time){
		super();
		this.hotelId=hotelId;
		this.score=score;
		this.review=review;
		this.userId=userId;
		this.userName=userName;
		this.time=time;
	}
	
	public int gethotelId(){
		return hotelId;
	}
	
	public void sethotelId(int hotelId){
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
	
	public String gettime(){
		return time;
	}
	
	public void settime(String time){
		this.time=time;
	}
	public ReviewVO toReviewvo(){
		ReviewVO vo=new ReviewVO();
		vo.hotelID=this.hotelId;
		vo.review=this.review;
		vo.score=this.score;
		vo.time=this.time;
		vo.userID=this.userId;
		vo.userName=this.userName;
	    return vo;
	}
}