package tiquartet.ServerModule.po;

import java.io.Serializable;

import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.vo.OrderVO;


public class OrderPO implements Serializable{
	//订单编号
	private long orderId;
	//订单状态
	private OrderStatus orderStatus;
	//最晚订单执行时间
	private String latestTime;
	//房间数量
	private int numberOfRoom;
	//入住人数
	private int numberOfPeople;
	//有无儿童
	private int child;
	//入住人真实姓名
	private String realName;
	//酒店编号
	private int hotelId;
	//用户编号
	private int userId;
	//用户名
	private String userName;
	//入住日期
	private String startTime;
	//离店日期
	private String leaveTime;
	//订单价格
	private double price;
	public OrderPO(OrderVO vo){
		this.child=vo.child;
		this.hotelId=vo.hotelId;
		this.latestTime=vo.latestTime;
		this.leaveTime=vo.leaveTime;
		this.numberOfPeople=vo.numberOfPeople;
		this.numberOfRoom=vo.numberOfRoom;
		this.orderId=vo.orderId;
		this.orderStatus=vo.orderStatus;
		this.price=vo.price;
		this.realName=vo.realName;
		this.startTime=vo.startTime;
		this.userId=vo.userId;
		this.userName=vo.userName;
	}
	public OrderPO(){
		
	}
	public OrderPO(long orderId,String latestTime,int numberOfRoom,int numberOfPeople,int child,String realName,int hotelId){
		super();
		this.orderId=orderId;
		this.latestTime=latestTime;
		this.numberOfRoom=numberOfRoom;
		this.numberOfPeople=numberOfPeople;
		this.child=child;
		this.realName=realName;
		this.hotelId=hotelId;
	}
	
	public long getorderId(){
		return orderId;
	}
	
	public void setorderId(int orderId){
		this.orderId=orderId;
	}
	
	public String getlatestTime(){
		return latestTime;
	}
	
	public void setlatestTime(String latestTime){
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
	
	public int getchild(){
		return child;
	}
	
	public void setchild(int child){
		this.child=child;
	}
	
	public String getrealName(){
		return realName;
	}
	
	public void setrealName(String realName){
		this.realName=realName;
	}
	
	public void sethotelId(int hotelId){
		this.hotelId=hotelId;
	}
	
	public int gethotelId(){
		return this.hotelId;
	}
	
	public void setuserId(int userId){
		this.userId=userId;
	}
	
	public int getuserId(){
		return userId;
	}
	
	public void setuserName(String userName){
		this.userName=userName;
	}
	
	public String getuserName(){
		return this.userName;
	}
	
	public void setstartTime(String startTime){
		this.startTime=startTime;
	}
	
	public String getstartTime(){
		return this.startTime;
	}
	
	public void setleaveTime(String leaveTime){
		this.leaveTime=leaveTime;
	}

	public String getleaveTime(){
		return this.leaveTime;
	}
	
	public void setprice(double price){
		this.price=price;
	}
	
	public double getprice(){
		return this.price;
	}
	
	public void setorderStatus(OrderStatus orderType){
		this.orderStatus=orderType;
	}
	
	public OrderStatus getorderStatus(){
		return this.orderStatus;
	}
	public OrderVO toOrderVO(){
		OrderVO vo=new OrderVO();
		vo.child=this.child;
		vo.hotelId=this.hotelId;
		vo.latestTime=this.latestTime;
		vo.leaveTime=this.leaveTime;
		vo.numberOfPeople=this.numberOfPeople;
		vo.numberOfRoom=this.numberOfRoom;
		vo.orderId=this.orderId;
		vo.realName=this.realName;
		vo.startTime=this.startTime;
		vo.userId=this.userId;
		vo.userName=this.userName;
		vo.orderStatus=this.orderStatus;
		return vo;
	}
	
}
