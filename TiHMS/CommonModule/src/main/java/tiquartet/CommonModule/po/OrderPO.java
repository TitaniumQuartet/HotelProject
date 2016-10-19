package tiquartet.CommonModule.po;

import java.io.Serializable;
import java.util.Calendar;


public class OrderPO implements Serializable{
	//订单编号
	private long orderId;
	//最晚订单执行时间
	private Calendar latestTime;
	//房间数量
	private int numberOfRoom;
	//入住人数
	private int numberOfPeople;
	//有无儿童
	private boolean child;
	//入住人真实姓名
	private String realName;
	
	public OrderPO(){
		
	}
	
	public OrderPO(long orderId,Calendar latestTime,int numberOfRoom,int numberOfPeople,boolean child,String realName){
		super();
		this.orderId=orderId;
		this.latestTime=latestTime;
		this.numberOfRoom=numberOfRoom;
		this.numberOfPeople=numberOfPeople;
		this.child=child;
		this.realName=realName;
	}
	
	public long getorderId(){
		return orderId;
	}
	
	public void setorderId(int orderId){
		this.orderId=orderId;
	}
	
	public Calendar getlatestTime(){
		return latestTime;
	}
	
	public void setlatestTime(Calendar latestTime){
		this.latestTime=latestTime;
	}
	
	public int getnumberOfRoom(){
		return numberOfRoom;
	}
	
	public void setnumberOfRoom(int numberOfRoom){
		this.numberOfRoom=numberOfRoom;
	}
	
	public int getnumberOfPeople(){
		return numberOfPeople;
	}
	
	public void setnumberOfPeople(int numberOfPeople){
		this.numberOfPeople=numberOfPeople;
	}
	
	public boolean getchild(){
		return child;
	}
	
	public void setchild(boolean child){
		this.child=child;
	}
	
	public String getrealName(){
		return realName;
	}
	
	public void setrealName(String realName){
		this.realName=realName;
	}
	
}
